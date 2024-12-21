package com.pscarpellini.models.vos

import java.math.BigDecimal
import java.time.LocalDateTime

data class ContratoVO(
    val id: Int,
    val preco: BigDecimal,
    val cliente: ClienteVO,
    val servicoVO: ServicoVO,
    val dataContrato: LocalDateTime
)