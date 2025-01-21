package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.enums.PerfisDeAcessoEnum
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import kotlinx.html.FlowContent

fun FlowContent.includeSelectDePerfis(
    label: String = "Tipo de conta",
    hint: String = "",
    isObrigatorio: Boolean = true
) {
    selectField(
        label = label,
        hint = hint,
        nomeDoCampo = "perfilDeAcesso",
        isObrigatorio = isObrigatorio,
        opcoes = PerfisDeAcessoEnum.obterPerfisDisponiveis().map { it.slug to it.nome }
    )
}