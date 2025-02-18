package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.ExtratoDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.ExtratosTable
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.utils.extratoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.ExtratoVO
import com.pscarpellini.models.vos.NovoExtratoVO
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime
import kotlin.Int

class ExtratosRepositoryPostgres : ExtratosRepository {

    override suspend fun carregarExtratos(nome: String, clienteId: Int): DbResponse<List<ExtratoVO>> = suspendTransaction {
        val contaResponsavel = ContasTable.alias("responsavel")
        val contaSaldo = ContasTable.alias("saldo")
        val promocao = PromocoesTable.alias("promocao")

        val extratos = ExtratosTable
            .join(contaSaldo, JoinType.INNER, additionalConstraint = { contaSaldo[ContasTable.id] eq ExtratosTable.contaSaldoId })
            .join(contaResponsavel, JoinType.INNER, additionalConstraint = { contaResponsavel[ContasTable.id] eq ExtratosTable.contaResponsavelId })
            .leftJoin(promocao, additionalConstraint = { promocao[PromocoesTable.id] eq ExtratosTable.promocaoId })
            .selectAll()
            .where {
                (contaSaldo[ContasTable.clienteId] eq clienteId)
                    .and(contaResponsavel[ContasTable.clienteId] eq clienteId)
                    .and(
                        (contaSaldo[ContasTable.nome].lowerCase().like("%${nome.lowercase()}%"))
                            .or(contaSaldo[ContasTable.usuario].lowerCase().like("%${nome.lowercase()}%"))
                            .or(contaSaldo[ContasTable.email].lowerCase().like("%${nome.lowercase()}%"))
                            .or(contaResponsavel[ContasTable.nome].lowerCase().like("%${nome.lowercase()}%"))
                            .or(contaResponsavel[ContasTable.usuario].lowerCase().like("%${nome.lowercase()}%"))
                            .or(contaResponsavel[ContasTable.email].lowerCase().like("%${nome.lowercase()}%"))
                    )
            }
            .map(::extratoDaoToModel)

        DbResponse.Successo(extratos)
    }

    override suspend fun carregarExtratosRecentes(clienteId: Int): DbResponse<List<ExtratoVO>> = suspendTransaction {
        val contaResponsavel = ContasTable.alias("responsavel")
        val contaSaldo = ContasTable.alias("saldo")
        val promocao = PromocoesTable.alias("promocao")

        val extratos = ExtratosTable
            .join(contaSaldo, JoinType.INNER, additionalConstraint = { contaSaldo[ContasTable.id] eq ExtratosTable.contaSaldoId })
            .join(contaResponsavel, JoinType.INNER, additionalConstraint = { contaResponsavel[ContasTable.id] eq ExtratosTable.contaResponsavelId })
            .leftJoin(promocao, additionalConstraint = { promocao[PromocoesTable.id] eq ExtratosTable.promocaoId })
            .selectAll()
            .where {
                (contaSaldo[ContasTable.clienteId] eq clienteId)
                    .and(contaResponsavel[ContasTable.clienteId] eq clienteId)
            }
            .map(::extratoDaoToModel)
            .sortedByDescending { it.dataCriacao }

        DbResponse.Successo(extratos)
    }

    override suspend fun criarRegistroDeExtrato(extrato: NovoExtratoVO): DbResponse<NovoExtratoVO> = suspendTransaction {
        val contaResponsavel = ContaDAO.findById(extrato.idContaResponsavel)
            ?: throw IllegalArgumentException("Conta do responsável não encontrado")
        val contaSaldo = ContaDAO.findById(extrato.idContaSaldo)
            ?: throw IllegalArgumentException("Conta do saldo não encontrado")
        val promocaoRelacionada = if(extrato.promocao != null) {
            PromocaoDAO.findById(extrato.promocao)
                ?: throw IllegalArgumentException("Promoção não encontrada")
        } else null

        runCatching {
            ExtratoDAO.new {
                contaResponsavelId = contaResponsavel.id
                contaSaldoId = contaSaldo.id
                promocaoId = promocaoRelacionada?.id
                dataCriacao = LocalDateTime.now()
                valor = extrato.valor
                isCredito = extrato.isCredito
            }
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.onSuccess {
            DbResponse.Successo(extrato)
        }
        DbResponse.Successo(extrato)
    }

}