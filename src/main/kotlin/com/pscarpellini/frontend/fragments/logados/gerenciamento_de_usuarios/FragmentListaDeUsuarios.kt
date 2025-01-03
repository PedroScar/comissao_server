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
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeListaDeUsuarios(
    contas: List<ContaVO>?
) {
    val usuarios: List<ContaVO> = contas ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = arrayListOf("Nome do promotor", "Tipo de conta", "Último acesso", "Ações"),
        linhas = usuarios.map { conta ->
            exibirUsuario(conta)
        },
        classes = "h-full w-full"
    )
}



private fun exibirUsuario(conta: ContaVO): List<FlowContent.() -> Unit> {
    return listOf(
        { +conta.nome },
        { +conta.usuario },
        { +conta.email },
        {
//            div(classes = "flex flex-row gap-2") {
//                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Editar"))
//                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Visualizar"))
//            }
        }
    )
}