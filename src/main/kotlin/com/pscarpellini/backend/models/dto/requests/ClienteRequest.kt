package com.pscarpellini.backend.models.dto.requests

import kotlinx.serialization.Serializable

@Serializable
data class ClienteRequest(
    val nome: String,
    val endereco: String,
    val cnpj: String,
    val email: String,
    val telefone: String,
    val status: String,
    val dataCriacao: String
)