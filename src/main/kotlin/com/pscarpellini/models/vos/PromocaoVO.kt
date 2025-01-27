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
    @Serializable(with = LocalDateTimeSerializer::class) val dataValidade: LocalDateTime? = null,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class) val dataDisponivel: LocalDateTime,
    val duracaoIndeterminada: Boolean,
    val exibirPreco: Boolean,
    val valorAnterior: Double?,
    val valorAtual: Double?,
) {
    val status: StatusPromocoesEnum
        get() {
            val today = LocalDateTime.now()
            return when {
                // Promoção cancelada (data de validade passou e está indisponível)
                dataValidade != null && today.isAfter(dataValidade) && today.isBefore(dataDisponivel) -> StatusPromocoesEnum.CANCELADA
                // Promoção ativa (duração indeterminada ou ainda válida)
                duracaoIndeterminada || (today.isAfter(dataDisponivel) && (dataValidade == null || today.isBefore(dataValidade))) -> StatusPromocoesEnum.ATIVA
                // Promoção agendada (não disponível ainda)
                today.isBefore(dataDisponivel) -> StatusPromocoesEnum.AGENDADA
                // Promoção encerrada (data de validade passou)
                dataValidade != null && today.isAfter(dataValidade) -> StatusPromocoesEnum.ENCERRADA
                // Caso padrão
                else -> StatusPromocoesEnum.ATIVA
            }
        }
}
