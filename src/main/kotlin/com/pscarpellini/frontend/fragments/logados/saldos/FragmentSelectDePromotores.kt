package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent
import kotlin.math.absoluteValue

fun FlowContent.includeSelectDePromotores(
    nomeDoCampo: String?,
    label: String?,
    hint: String?,
    isObrigatorio: Boolean = false,
    promotores: List<ContaVO>,
    hxOnChangePath: String?,
    hxTarget: String?,
    classes: String = "",
) {
    selectField(
        label = label ?: "Promotor",
        hint = hint ?: "Nenhum promotor selecionado",
        nomeDoCampo = nomeDoCampo ?: "promotor",
        isObrigatorio = isObrigatorio,
        hxOnChangePath = hxOnChangePath,
        hxTarget = hxTarget,
        opcoes = promotores.map { it.id.toString() to it.nome },
        classes = classes
    )
}