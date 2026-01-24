package com.pscarpellini.models.response

import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.serialization.Serializable

@Serializable
data class PromocoesPaginacao(
    val lista: List<PromocaoVO>,
    val novaPagina: Boolean
)
