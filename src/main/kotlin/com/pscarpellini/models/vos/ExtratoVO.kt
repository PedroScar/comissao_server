package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

data class ExtratoVO(
    val contaCriacaoId: Int,
    val contaDonoId: Int,
    val promocaoId: Int? = null,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime? = null,
    val valor: Double,
    val isCredito: Boolean
)