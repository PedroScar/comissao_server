package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.frontend.enums.*
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
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.css.h5
import kotlinx.html.*

fun FlowContent.includeCardDePerfis(
    perfisDeAcesso: List<PerfilDeAcessoVO>?
) {
    div(classes = "${CoresEnum.BRAND_LIGHT.bg} ${ArredondamentosEnum.MD} py-4 px-6 w-full") {
        span { +"Tipos de contas:" }
        ul (classes = "list-disc ms-6") {
            perfisDeAcesso?.forEach {
                li {
                    b { +"${it.nome}: " }
                    +it.descricao
                }
            }
        }
    }
}