package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class SaldoVO(
    val conta: ContaVO,
    val saldo: Double,
)
