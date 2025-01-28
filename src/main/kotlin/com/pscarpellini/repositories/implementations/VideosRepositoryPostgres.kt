package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.VideoDAO
import com.pscarpellini.database.tables.VideosTable
import com.pscarpellini.database.utils.videoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.response.VideosPaginacao
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.VideosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.and

class VideosRepositoryPostgres : VideosRepository {
    override suspend fun carregarVideosDestaque(clienteId: Int): DbResponse<List<VideoVO>> = suspendTransaction {
        val listaVideos = runCatching {
            VideoDAO
                .find { (VideosTable.clienteId eq clienteId).and(VideosTable.destaque eq true) }
                .map(::videoDaoToModel)
        }.onFailure { DbResponse.Erro(message = "${it.message}", data = null) }.getOrThrow()

        DbResponse.Successo(listaVideos)
    }

    override suspend fun carregarVideosPaginacao(clienteId: Int, pagina: Int): DbResponse<VideosPaginacao> {
        val itensPorPagina = 5
        val offset = ((pagina - 1) * itensPorPagina).toLong()

        val listaVideos = runCatching {
            VideoDAO
                .find { VideosTable.clienteId eq clienteId }
                .limit(itensPorPagina + 1, offset)
                .map(::videoDaoToModel)
        }.onFailure {
            return DbResponse.Erro(message = "${it.message}", data = null)
        }.getOrThrow()

        val temNovaPagina = listaVideos.size > itensPorPagina

        return DbResponse.Successo(
            VideosPaginacao(
                lista = listaVideos.take(itensPorPagina),
                novaPagina = temNovaPagina
            )
        )
    }
}