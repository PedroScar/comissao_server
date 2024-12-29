package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.promocaoDaoToModel
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.suspendTransaction

class PromocoesRepositoryPostgres : PromocoesRepository {
    override suspend fun carregarPromocoes(clienteId: Int): DbResponse<List<PromocaoVO>> = suspendTransaction {
        val listaPromocoes = PromocaoDAO
            .find { (PromocoesTable.clienteId eq clienteId) }
            .map(::promocaoDaoToModel)

        return@suspendTransaction if (listaPromocoes.isEmpty()) {
            DbResponse.Erro(null, "Lista vazia")
        } else {
            DbResponse.Successo(listaPromocoes)
        }
    }
}