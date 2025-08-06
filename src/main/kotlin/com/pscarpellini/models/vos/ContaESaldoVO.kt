package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class ContaESaldoVO(
    val id: Int? = null,
    val nome: String,
    val foto: String = "",
    val saldo: Double,
    val cpf: String,
    val email: String,
    val telefone: String,
    val usuario: String,
    val status: String,
    val tipoConta: String,
    val senha: String,
)
