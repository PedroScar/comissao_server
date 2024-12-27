package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class ContaVO(
    val clientId: Int,
    val nome: String,
    val foto: String = "",
    val endereco: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val usuario: String,
    val status: String,
    val tipoConta: String,
)
