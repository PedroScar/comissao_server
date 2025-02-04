package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.ExtratosTable
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.utils.extratoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ExtratoVO
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
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

}