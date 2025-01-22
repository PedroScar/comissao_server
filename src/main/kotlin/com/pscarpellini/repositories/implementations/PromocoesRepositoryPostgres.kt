package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import java.time.LocalDateTime

class PromocoesRepositoryPostgres : PromocoesRepository {
    override suspend fun carregarPromocoes(clienteId: Int): DbResponse<List<PromocaoVO>> = suspendTransaction {
        val listaPromocoes = PromocaoDAO
            .find { (PromocoesTable.clienteId eq clienteId) }
            .map(::promocaoDaoToModel)

        DbResponse.Successo(listaPromocoes)
    }

    override suspend fun contagemDePromocoesAtivas(clienteId: Int): DbResponse<Int> = suspendTransaction {
        val quantidadePromocoesAtivas = PromocaoDAO
            .count (
                (PromocoesTable.clienteId eq clienteId)
//                    .and(PromocoesTable.status eq "ATIVA")
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
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.onSuccess {
            DbResponse.Successo(promocao)
        }
        DbResponse.Successo(promocao)
    }
}