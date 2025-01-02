package com.pscarpellini.models.vos

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PromocaoVO(
    val clientId: Int,
    val titulo: String,
    val subtitulo: String,
    val conteudo: String,
    val imagem: String,
    @Contextual val dataValidade: LocalDateTime,
    @Contextual val dataCriacao: LocalDateTime,
)
