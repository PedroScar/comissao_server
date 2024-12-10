package com.pscarpellini.models

import java.time.LocalDateTime

data class Servico(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime
)