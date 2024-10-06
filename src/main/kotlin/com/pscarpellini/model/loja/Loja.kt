package com.pscarpellini.model.loja

import com.pscarpellini.enums.ContaStatusEnum
import kotlinx.serialization.Serializable

@Serializable
data class Loja(
    val id: Int,
    val nome: String,
    val endereco: String,
    val cnpj: String,
    val email: String,
    val telefone: String,
    val status: ContaStatusEnum
)
