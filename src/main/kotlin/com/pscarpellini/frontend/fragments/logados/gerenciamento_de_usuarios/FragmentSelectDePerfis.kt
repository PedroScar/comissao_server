package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.PosicoesDropdownEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownDivider
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownItem
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeSelectDePerfis(
    label: String = "Tipo de conta",
    hint: String = "Funcionário",
    isObrigatorio: Boolean = true,
    opcoes: ArrayList<Pair<String, String>> = arrayListOf()
) {
    selectField(
        label = label,
        hint = hint,
        isObrigatorio = isObrigatorio,
        opcoes = opcoes
    )
}