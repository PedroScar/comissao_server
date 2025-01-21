package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ServicoVO(
    val id: Int,
    val nome: String,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime,
)