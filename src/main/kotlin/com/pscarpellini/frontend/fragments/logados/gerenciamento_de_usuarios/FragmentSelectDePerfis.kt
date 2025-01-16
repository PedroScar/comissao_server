package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import kotlinx.html.FlowContent

fun FlowContent.includeSelectDePerfis(
    label: String = "Tipo de conta",
    hint: String = "",
    isObrigatorio: Boolean = true,
    perfisDeAcesso: List<PerfilDeAcessoVO>?
) {
    selectField(
        label = label,
        hint = hint,
        nomeDoCampo = "perfilDeAcesso",
        isObrigatorio = isObrigatorio,
        opcoes = perfisDeAcesso?.map { it.id.toString() to it.nome } ?: arrayListOf()
    )
}