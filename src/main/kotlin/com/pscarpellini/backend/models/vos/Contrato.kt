package com.pscarpellini.backend.models.vos

import java.math.BigDecimal
import java.time.LocalDateTime

data class Contrato(
    val id: Int,
    val preco: BigDecimal,
    val cliente: ClienteVO,
    val servico: Servico,
    val dataContrato: LocalDateTime
)