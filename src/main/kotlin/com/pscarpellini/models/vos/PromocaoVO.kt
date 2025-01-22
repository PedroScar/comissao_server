package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
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
    @Serializable(with = LocalDateTimeSerializer::class) val dataDisponivel: LocalDateTime,
    val duracaoIndeterminada: Boolean,
    val exibirPreco: Boolean,
    val valorAnterior: Double,
    val valorAtual: Double,
) {
    val status: StatusPromocoesEnum
        get() {
            val today = LocalDateTime.now()
            return when {
                duracaoIndeterminada -> StatusPromocoesEnum.ATIVA
                today.isBefore(dataDisponivel) -> StatusPromocoesEnum.AGENDADA
                today.isAfter(dataValidade) -> StatusPromocoesEnum.ENCERRADA
                else -> StatusPromocoesEnum.ATIVA
            }
        }
}
