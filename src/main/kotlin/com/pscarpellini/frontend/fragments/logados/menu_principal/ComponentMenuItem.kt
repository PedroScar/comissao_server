package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.b
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeMenuItem(
    item: ItensMenuEnum,
    isSelecionado: Boolean = false,
) {
    div(
        classes = "flex h-12 items-center w-full gap-2 px-4 py-3 rounded-pill cursor-pointer transition-all duration-300 text-sm ${if (isSelecionado) "bg-high-medium font-bold" else "bg-transparent hover:bg-high-medium font-semibold"}"
    ) {
        attributes["hx-post"] = item.pagina.path
        attributes["hx-trigger"] = "click"
        attributes["hx-target"] = "#conteudo-interno"
        attributes["hx-replace-url"] = item.pagina.path
        attributes["hx-swap"] = "innerHTML"

        icone(item.icone ?: IconesEnum.MENU, size = 1.6f, usarPadding = false, usarPreenchido = isSelecionado)
        if (isSelecionado) b { +item.nome }
        else span { +item.nome }
    }
}