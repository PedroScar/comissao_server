package com.pscarpellini.backend.models

import java.math.BigDecimal
import java.time.LocalDateTime

data class Contrato(
    val id: Int,
    val preco: BigDecimal,
    val cliente: Cliente,
    val servico: Servico,
    val dataContrato: LocalDateTime
)