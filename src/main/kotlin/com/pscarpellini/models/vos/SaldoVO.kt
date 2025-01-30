package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class SaldoVO(
    val contaId: Int,
    val saldo: Double,
)
