package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent

fun FlowContent.includeSelectDePromocoes(
    nomeDoCampo: String?,
    label: String?,
    hint: String?,
    isObrigatorio: Boolean = false,
    promocoes: List<PromocaoVO>,
    classes: String = "",
) {
    selectField(
        label = label ?: "Promoção",
        hint = hint ?: "Nenhuma promoção selecionada",
        nomeDoCampo = nomeDoCampo ?: "promocao",
        isObrigatorio = isObrigatorio,
        opcoes = promocoes.map { it.id.toString() to it.titulo },
        classes = classes
    )
}