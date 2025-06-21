package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class CookieVO(
    val idUsuario: Int,
    val idCliente: Int
)