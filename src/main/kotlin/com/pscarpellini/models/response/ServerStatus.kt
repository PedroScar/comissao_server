package com.pscarpellini.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ServerStatus(
    val ip: String,
    val uptime: Long,
    val usuariosCadastrados: Int,
    val clientesCadastrados: Int,
)