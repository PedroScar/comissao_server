package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class ClienteVO(
    val id: Int? = null,
    val nome: String,
    val endereco: String,
    val cnpj: String,
    val email: String,
    val telefone: String,
    var status: String,
    val logo: String?,
    val isPontos: Boolean = false
)