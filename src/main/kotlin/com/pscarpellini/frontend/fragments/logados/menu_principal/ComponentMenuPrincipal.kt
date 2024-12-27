package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposItensMenuEnum
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.nav
import org.h2.engine.Session


fun FlowContent.includeMenuPrincipal(
    sessao: SessaoUsuarioVO,
) {
    nav(classes = "fixed flex flex-col h-full w-80 p-4 bg-high-pure px-6") {
        includeMenuSeletorProduto(nome = "Comissão", classes = "")
        includeMenuCliente("Pinturas Prime", classes = "mt-4")

        div("flex-grow overflow-y-auto space-y-1 mt-4") {
            sessao.menusDisponiveis.forEach {
                if(it.tipo == TiposItensMenuEnum.ITEM) includeMenuItem(nome = it.nome, link = it.caminho, icone = it.icone ?: IconesEnum.MENU, isSelecionado = it == sessao.menuSelecionado)
                else includeMenuCategoria(it.nome)
            }
        }
    }
}