package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.daos.VideoDAO
import com.pscarpellini.database.tables.VideosTable
import com.pscarpellini.database.utils.videoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.response.VideosPaginacao
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.VideosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.lowerCase

class VideosRepositoryPostgres : VideosRepository {
    override suspend fun editarVideo(video: VideoVO): DbResponse<VideoVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(video.clientId)
            ?: throw IllegalArgumentException("Cliente com ID ${video.clientId} não encontrado")

        val videoExistente = VideoDAO.findById(video.id ?: -4)
            ?: return@suspendTransaction DbResponse.Erro(null, "Vídeo com ID ${video.id} não encontrado")

        runCatching {
            videoExistente.apply {
                clienteId = cliente
                titulo = video.titulo
                video_id = video.video_id
                destaque = video.destaque
                habilitado = video.habilitado
                thumb = video.thumb
            }
            DbResponse.Successo(videoDaoToModel(videoExistente))
        }.onFailure {
            println("==================")
            println("ERRO DB: ${it.message}")
            println("==================")
            DbResponse.Erro(null, message = it.message.toString())
        }.getOrDefault(DbResponse.Erro(null, message = "Ops... algo de errado aconteceu!"))
    }

    override suspend fun desabilitarVideo(videoId: Int, clienteId: Int): DbResponse<VideoVO> = suspendTransaction {
        val videoDAO = VideoDAO.find {
            (VideosTable.id eq videoId).and(VideosTable.clienteId eq clienteId)
        }.firstOrNull()

        runCatching {
            if (videoDAO == null) {
                DbResponse.Erro(message = "Video não encontrado")
            } else {
                videoDAO.apply { habilitado = false }
                DbResponse.Successo(videoDaoToModel(videoDAO))
            }
        }.onFailure {
            it.printStackTrace()
        }.getOrDefault(DbResponse.Erro(message = "Erro ao desabilitar vídeo"))
    }

    override suspend fun removerVideo(videoId: Int, clienteId: Int): DbResponse<Unit> = suspendTransaction {
        val videoDAO = VideoDAO.find {
            (VideosTable.id eq videoId).and(VideosTable.clienteId eq clienteId)
        }.firstOrNull()

        runCatching {
            if (videoDAO == null) {
                DbResponse.Erro(message = "Vídeo não encontrado")
            } else {
                videoDAO.delete()
                DbResponse.Successo(Unit)
            }
        }.onFailure {
            it.printStackTrace()
        }.getOrDefault(DbResponse.Erro(message = "Erro ao deletar vídeo"))
    }

    override suspend fun criarVideo(video: VideoVO): DbResponse<VideoVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(video.clientId)
            ?: throw IllegalArgumentException("Cliente com ID ${video.clientId} não encontrado")

        runCatching {
            VideoDAO.new {
                clienteId = cliente
                titulo = video.titulo
                video_id = video.video_id
                destaque = video.destaque
                habilitado = video.habilitado
                thumb = video.thumb
            }
            DbResponse.Successo(video)
        }.onFailure {
            it.printStackTrace()
            DbResponse.Erro(null, message = it.message.toString())
        }.getOrDefault(DbResponse.Erro(null, message = "Ops... algo de errado aconteceu!"))
    }

    override suspend fun carregarVideo(videoId: Int, clienteId: Int): DbResponse<VideoVO> = suspendTransaction {
        val video = runCatching {
            VideoDAO
                .find { (VideosTable.id eq videoId) and (VideosTable.clienteId eq clienteId) }
                .firstOrNull()
                ?.let(::videoDaoToModel)
        }.onFailure { it.printStackTrace() }.getOrThrow()

        if (video != null) DbResponse.Successo(video)
        else DbResponse.Erro(message = "Video não encontrado.")

    }

    override suspend fun carregarListaVideos(clienteId: Int, termo: String): DbResponse<List<VideoVO>> =
        suspendTransaction {
            val listaVideos = runCatching {
                VideoDAO
                    .find {
                        (VideosTable.clienteId eq clienteId)
                            .and(VideosTable.titulo.lowerCase().like("%${termo.lowercase()}%"))
                    }
                    .reversed()
                    .map(::videoDaoToModel)
            }.onFailure { DbResponse.Erro(message = "${it.message}", data = null) }.getOrThrow()

            DbResponse.Successo(listaVideos)
        }

    override suspend fun carregarVideosDestaque(clienteId: Int): DbResponse<List<VideoVO>> = suspendTransaction {
        val listaVideos = runCatching {
            VideoDAO
                .find { (VideosTable.clienteId eq clienteId).and(VideosTable.destaque eq true) }
                .map(::videoDaoToModel)
        }.onFailure { DbResponse.Erro(message = "${it.message}", data = null) }.getOrThrow()

        DbResponse.Successo(listaVideos)
    }

    override suspend fun carregarVideosPaginacao(clienteId: Int, pagina: Int): DbResponse<VideosPaginacao> =
        suspendTransaction {
            val itensPorPagina = 5
            val offset = ((pagina - 1) * itensPorPagina).toLong()

            val listaVideos = runCatching {
                VideoDAO
                    .find { VideosTable.clienteId eq clienteId }
                    .limit(itensPorPagina + 1, offset)
                    .map(::videoDaoToModel)
            }.onFailure { DbResponse.Erro(message = "${it.message}", data = null) }.getOrThrow()

            val temNovaPagina = listaVideos.size > itensPorPagina

            DbResponse.Successo(
                VideosPaginacao(
                    lista = listaVideos.take(itensPorPagina),
                    novaPagina = temNovaPagina
                )
            )
        }
}