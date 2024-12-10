package com.pscarpellini.models

import java.time.LocalDateTime

data class Cliente(
    val id: Int,
    val nome: String,
    val endereco: String,
    val cnpj: String,
    val email: String,
    val telefone: String,
    val status: String,
    val dataCriacao: LocalDateTime
)