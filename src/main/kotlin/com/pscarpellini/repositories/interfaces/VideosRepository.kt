package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.VideoVO

interface VideosRepository {
    suspend fun carregarVideos(clienteId: Int): DbResponse<List<VideoVO>>
}