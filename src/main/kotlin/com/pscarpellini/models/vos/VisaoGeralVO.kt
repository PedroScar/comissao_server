package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class VisaoGeralVO(
    val quantidadePromocoesAtivas: Int,
    val quantidadePromotores: Int,
    val valorComissoesMesAtual: Double,
)
