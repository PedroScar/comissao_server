package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

data class NovoExtratoVO(
    val idContaResponsavel: Int,
    val idContaSaldo: Int,
    val promocao: Int? = null,
    val valor: Double,
    val isCredito: Boolean
)