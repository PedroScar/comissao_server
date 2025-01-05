package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposItensMenuEnum
import com.pscarpellini.frontend.fragments.geral.toast.toastContainer
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.nav
import org.h2.engine.Session


fun FlowContent.includeMenuPrincipal(
    sessao: SessaoUsuarioVO,
) {
    nav(classes = "fixed flex flex-col h-full w-80 p-4 bg-high-pure px-6") {
        attributes["id"] = "menu-principal"
        attributes["hx-swap-oob"] = "true"
        includeMenuSeletorProduto(nome = "Comissão", classes = "")
        includeMenuCliente("Pinturas Prime", classes = "mt-4")

        div("flex-grow overflow-y-auto space-y-1 mt-4") {
            sessao.menusDisponiveis.forEach {
                if(it.tipo == TiposItensMenuEnum.ITEM) includeMenuItem(item = it, isSelecionado = it == sessao.menuSelecionado)
                else includeMenuCategoria(it.nome)
            }
        }
    }
}