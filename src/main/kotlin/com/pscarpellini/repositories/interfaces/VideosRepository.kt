package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.response.VideosPaginacao
import com.pscarpellini.models.vos.VideoVO

interface VideosRepository {
    suspend fun carregarVideosDestaque(clienteId: Int): DbResponse<List<VideoVO>>
    suspend fun carregarVideosPaginacao(clienteId: Int, pagina: Int): DbResponse<VideosPaginacao>
}