package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PromocaoVO(
    val id: Int? = null,
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
            val now = LocalDateTime.now()
            return when {
                // Promoção cancelada (já passou da data de validade e está indisponível)
                (dataValidade != null) && now.isAfter(dataValidade) && now.isBefore(dataDisponivel) -> StatusPromocoesEnum.CANCELADA

                // Promoção encerrada (fora do intervalo ou validade já passou)
                (dataValidade != null) && now.isAfter(dataValidade) -> StatusPromocoesEnum.ENCERRADA

                // Promoção ativa (duração indeterminada ou dentro do intervalo de validade)
                duracaoIndeterminada && now.isAfter(dataDisponivel) && dataValidade == null
                        || (dataValidade != null) && now.isAfter(dataDisponivel) && now.isBefore(dataValidade) -> StatusPromocoesEnum.ATIVA

                // Promoção agendada (não disponível ainda)
                now < dataDisponivel -> StatusPromocoesEnum.AGENDADA

                // Promoção encerrada (fora do intervalo ou validade já passou)
                else -> StatusPromocoesEnum.ENCERRADA
            }
        }
}
