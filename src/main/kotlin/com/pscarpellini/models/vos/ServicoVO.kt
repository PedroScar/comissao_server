package com.pscarpellini.models.vos

import java.time.LocalDateTime

data class ServicoVO(
    val id: Int,
    val nome: String,
    val dataCriacao: LocalDateTime
)