package com.pscarpellini.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class ClienteComContaResponse(
    val clienteId: Int,
    val clienteNome: String,
    val usuarioAdministrador: String,
    val senhaAdministrador: String,
    val emailAdministrador: String,
    val mensagem: String
)
