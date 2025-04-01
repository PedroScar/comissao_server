package com.pscarpellini.models.response

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ExtratoMobile(
    val promocaoId: Int,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime,
    val valor: Double,
    val isCredito: Boolean
)