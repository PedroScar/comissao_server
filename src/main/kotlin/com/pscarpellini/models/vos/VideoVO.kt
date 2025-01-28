package com.pscarpellini.models.vos

data class VideoVO(
    val clientId: Int,
    val titulo: String,
    val video_id: String,
    val habilitado: Boolean,
    val thumb: String,
)
