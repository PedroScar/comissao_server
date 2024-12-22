package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposItensMenuEnum
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import org.h2.engine.Session


fun FlowContent.includeMenuPrincipal(
    sessao: SessaoUsuarioVO,
) {
    div(classes = "flex flex-col h-screen w-80 p-4 bg-high-pure px-6") {
        div("flex items-center gap-2 font-bold text-lg mb-4") {
            +"🔗 Comissão"
        }

        div("flex-grow overflow-y-auto space-y-1") {
            sessao.menusDisponiveis.forEach {
                if(it.tipo == TiposItensMenuEnum.ITEM) includeMenuItem(nome = it.nome, icone = it.icone ?: IconesEnum.MENU, link = "")
                else includeMenuCategoria(it.nome)
            }
        }
    }
}