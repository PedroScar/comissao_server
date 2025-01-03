package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PerfilDeAcessoVO(
    val id: Int,
    val nome: String,
    val descricao: String,
)
