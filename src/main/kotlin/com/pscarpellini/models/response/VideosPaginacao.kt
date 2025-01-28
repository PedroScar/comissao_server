package com.pscarpellini.models.response

import com.pscarpellini.models.vos.VideoVO
import kotlinx.serialization.Serializable

@Serializable
data class VideosPaginacao(
    val lista: List<VideoVO>,
    val novaPagina: Boolean
)
