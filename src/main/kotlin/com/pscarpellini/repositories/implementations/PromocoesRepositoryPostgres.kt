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
        val listaPromocoes = runCatching {
            PromocaoDAO
                .find { (PromocoesTable.clienteId eq clienteId) }
        }.onFailure {
            println("Erro DB: ${it.message}")
            println(it.stackTrace.toString())
            return@suspendTransaction DbResponse.Erro(null, "Erro DB: ${it.message}\n\n\n${it.stackTrace}")
        }.getOrDefault(emptyList())

        listaPromocoes.forEach { pDao ->
            println("clienteId ${pDao.clienteId}\n" +
                    "titulo ${pDao.titulo}\n" +
                    "subtitulo ${pDao.subtitulo}\n" +
                    "conteudo ${pDao.conteudo}\n" +
                    "imagem ${pDao.imagem}\n" +
                    "dataValidade ${pDao.dataValidade}\n" +
                    "dataCriacao ${pDao.dataCriacao}\n")
        }

        var listaFinal: List<PromocaoVO> = emptyList()

        runCatching {
            listaFinal = listaPromocoes.map(::promocaoDaoToModel)
        }.onFailure {
            println("Erro no mapper: ${it.message}")
        }

       return@suspendTransaction if (listaFinal.isEmpty()) {
            DbResponse.Erro(null, "Lista vazia")
        } else {
            DbResponse.Successo(listaFinal)
        }
    }
}