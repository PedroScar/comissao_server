package com.pscarpellini.backend.models.vos

import java.time.LocalDateTime

data class Servico(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime
)