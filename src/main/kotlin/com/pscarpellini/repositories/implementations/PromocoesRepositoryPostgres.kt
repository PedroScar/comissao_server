package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and

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
}