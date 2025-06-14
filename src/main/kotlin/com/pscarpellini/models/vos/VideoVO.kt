package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class VideoVO(
    val id: Int? = null,
    val clientId: Int,
    val titulo: String,
    val video_id: String,
    val habilitado: Boolean,
    val destaque: Boolean,
    val thumb: String,
)