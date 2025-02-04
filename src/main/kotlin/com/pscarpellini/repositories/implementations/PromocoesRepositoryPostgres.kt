package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greater
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.or
import java.time.LocalDateTime

class PromocoesRepositoryPostgres : PromocoesRepository {
    override suspend fun carregarPromocoes(termo: String, clienteId: Int): DbResponse<List<PromocaoVO>> = suspendTransaction {
        val now = LocalDateTime.now()

        val listaPromocoes = runCatching {
            PromocaoDAO
                .find {
                    (PromocoesTable.clienteId eq clienteId)
                        .and(
                            (PromocoesTable.titulo.lowerCase().like("%${termo.lowercase()}%"))
                                .or(PromocoesTable.subtitulo.lowerCase().like("%${termo.lowercase()}%"))
                                .or(PromocoesTable.conteudo.lowerCase().like("%${termo.lowercase()}%"))
                        )
                }
                .sortedBy {
                    when {
                        // Promoção cancelada (já passou da data de validade e está indisponível)
                        (it.dataValidade != null) && now.isAfter(it.dataValidade) && now.isBefore(it.dataDisponivel) -> 4
                        // Promoção encerrada (fora do intervalo ou validade já passou)
                        (it.dataValidade != null) && now.isAfter(it.dataValidade) -> 3
                        // Promoção ativa (duração indeterminada ou dentro do intervalo de validade)
                        it.duracaoIndeterminada && now.isAfter(it.dataDisponivel) && it.dataValidade == null
                                || (it.dataValidade != null) && now.isAfter(it.dataDisponivel) && now.isBefore(it.dataValidade) -> 1
                        // Promoção agendada (não disponível ainda)
                        now < it.dataDisponivel -> 2
                        // Promoção encerrada (fora do intervalo ou validade já passou)
                        else -> 3
                    }
                }
                .map(::promocaoDaoToModel)
        }.onFailure { it.printStackTrace() }.getOrThrow()
        DbResponse.Successo(listaPromocoes)
    }

    override suspend fun carregarPromocoesMaisUtilizadas(clienteId: Int): DbResponse<List<PromocaoVO>> = suspendTransaction {
        val listaPromocoes = runCatching {
            PromocaoDAO
                .find { (PromocoesTable.clienteId eq clienteId) }
                .map(::promocaoDaoToModel)
        }.onFailure { it.printStackTrace() }.getOrThrow()
        DbResponse.Successo(listaPromocoes)
    }

    override suspend fun carregarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO> = suspendTransaction {
        val promocao = runCatching {
            PromocaoDAO
                .find { (PromocoesTable.id eq promocaoId) and (PromocoesTable.clienteId eq clienteId) }
                .firstOrNull() // Garante que será retornada no máximo uma promoção
                ?.let(::promocaoDaoToModel) // Converte para o modelo caso exista
        }.onFailure { it.printStackTrace() }.getOrThrow()

        // Verifica se a promoção foi encontrada e responde adequadamente
        if (promocao != null) DbResponse.Successo(promocao)
        else DbResponse.Erro(message = "Promoção não encontrada ou não pertence ao cliente especificado.")
    }

    override suspend fun contarPromocoesAtivas(clienteId: Int): DbResponse<Int> = suspendTransaction {
        val now = LocalDateTime.now()
        val quantidadePromocoesAtivas = PromocaoDAO
            .count(
                (PromocoesTable.clienteId eq clienteId)
                    .and(PromocoesTable.dataDisponivel.lessEq(now))
                    .and((PromocoesTable.duracaoIndeterminada eq true) or (PromocoesTable.dataValidade greater now))
            )

        DbResponse.Successo(quantidadePromocoesAtivas.toInt())
    }

    override suspend fun criarPromocao(promocao: PromocaoVO): DbResponse<PromocaoVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(promocao.clientId)
            ?: throw IllegalArgumentException("Cliente com ID ${promocao.clientId} não encontrado")

        runCatching {
            PromocaoDAO.new {
                clienteId = cliente
                titulo = promocao.titulo
                subtitulo = promocao.subtitulo
                conteudo = promocao.conteudo
                imagem = promocao.imagem
                dataValidade = promocao.dataValidade
                dataCriacao = promocao.dataCriacao
                dataDisponivel = promocao.dataDisponivel
                duracaoIndeterminada = promocao.duracaoIndeterminada
                exibirPreco = promocao.exibirPreco
                valorAnterior = promocao.valorAnterior
                valorAtual = promocao.valorAtual
            }
        }.onFailure { it.printStackTrace() }.onSuccess {
            DbResponse.Successo(promocao)
        }
        DbResponse.Successo(promocao)
    }

    override suspend fun encerrarPromocao(promocaoId: Int, clienteId: Int): DbResponse<PromocaoVO> = suspendTransaction {
        val promocaoDAO = PromocaoDAO.find {
            (PromocoesTable.id eq promocaoId).and(PromocoesTable.clienteId eq clienteId)
        }.firstOrNull()

        if (promocaoDAO == null) return@suspendTransaction DbResponse.Erro(message = "Promoção não encontrada.")

        runCatching {
            promocaoDAO.apply {
                // Marca a promoção como encerrada
                dataValidade = LocalDateTime.now().minusSeconds(1) // Ajusta a validade para antes do momento atual
            }
        }.onFailure {
            it.printStackTrace()
            return@suspendTransaction DbResponse.Erro(message = "Erro ao encerrar a promoção.")
        }

        DbResponse.Successo(promocaoDaoToModel(promocaoDAO))
    }
}