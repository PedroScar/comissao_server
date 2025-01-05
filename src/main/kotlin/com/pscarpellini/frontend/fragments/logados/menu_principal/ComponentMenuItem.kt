package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.*

fun FlowContent.includeMenuItem(
    item: ItensMenuEnum,
    isSelecionado: Boolean = false,
) {
    div(
        classes = "flex items-center w-full gap-2 px-4 py-3 rounded-pill cursor-pointer transition-all duration-300 text-sm ${if(isSelecionado) "bg-high-medium font-bold" else "bg-transparent hover:bg-high-medium font-semibold"}"
    ) {
        attributes["hx-post"] = item.caminho
        attributes["hx-trigger"] = "click"
        attributes["hx-target"] = "#conteudo-interno"
        attributes["hx-replace-url"] = item.caminho
        attributes["hx-swap"] = "innerHTML"

        icone(item.icone ?: IconesEnum.MENU, usarPadding = false, usarPreenchido = isSelecionado)
        if(isSelecionado) b { +item.nome }
        else span { +item.nome }
    }
}