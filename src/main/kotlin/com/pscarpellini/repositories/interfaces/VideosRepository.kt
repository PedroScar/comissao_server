package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.response.VideosPaginacao
import com.pscarpellini.models.vos.VideoVO

interface VideosRepository {
    suspend fun editarVideo(video: VideoVO): DbResponse<VideoVO>
    suspend fun habilitarDesabilitarVideo(habilitar: Boolean, videoId: Int, clienteId: Int): DbResponse<VideoVO>
    suspend fun removerVideo(videoId: Int, clienteId: Int): DbResponse<Unit>
    suspend fun criarVideo(video: VideoVO): DbResponse<VideoVO>
    suspend fun carregarVideo(videoId: Int, clienteId: Int): DbResponse<VideoVO>
    suspend fun carregarListaVideos(clienteId: Int, termo: String): DbResponse<List<VideoVO>>
    suspend fun carregarVideosDestaque(clienteId: Int): DbResponse<List<VideoVO>>
    suspend fun carregarVideosPaginacao(clienteId: Int, pagina: Int): DbResponse<VideosPaginacao>
}