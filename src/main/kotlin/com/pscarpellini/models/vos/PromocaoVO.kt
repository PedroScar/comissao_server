package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PromocaoVO(
    val clientId: Int,
    val titulo: String,
    val subtitulo: String,
    val conteudo: String,
    val imagem: String,
    @Serializable(with = LocalDateTimeSerializer::class) val dataValidade: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val dataVisivel: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val dataDisponivel: LocalDateTime,
    val status: String,
    val duracaoIndeterminada: Boolean,
)
