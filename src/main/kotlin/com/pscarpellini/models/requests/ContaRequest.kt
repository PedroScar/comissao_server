package com.pscarpellini.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class ContaRequest(
    val clientId: Int,
    val nome: String,
    val endereco: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val usuario: String,
    val senha: String,
    val status: String,
    val tipoConta: String,
)