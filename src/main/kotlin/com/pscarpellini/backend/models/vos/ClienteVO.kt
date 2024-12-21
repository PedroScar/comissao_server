package com.pscarpellini.backend.models.vos

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ClienteVO(
    val id: Int,
    val nome: String,
    val endereco: String,
    val cnpj: String,
    val email: String,
    val telefone: String,
    val status: String
)