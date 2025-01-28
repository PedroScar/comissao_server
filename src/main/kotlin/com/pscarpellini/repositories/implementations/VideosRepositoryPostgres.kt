package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.VideoDAO
import com.pscarpellini.database.tables.VideosTable
import com.pscarpellini.database.utils.videoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.VideosRepository
import com.pscarpellini.suspendTransaction

class VideosRepositoryPostgres : VideosRepository {
    override suspend fun carregarVideos(clienteId: Int): DbResponse<List<VideoVO>> = suspendTransaction {
        val listaPromocoes = runCatching {
            VideoDAO
                .find { VideosTable.clienteId eq clienteId }
                .map(::videoDaoToModel)
        }.onFailure { DbResponse.Erro(message = "${it.message}", data = null) }.getOrThrow()

        DbResponse.Successo(listaPromocoes)
    }
}