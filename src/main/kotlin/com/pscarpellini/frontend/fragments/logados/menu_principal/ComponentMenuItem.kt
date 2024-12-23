package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.b
import kotlinx.html.span

fun FlowContent.includeMenuItem(
    nome: String,
    isSelecionado: Boolean = false,
    icone: IconesEnum,
    link: String,
) {
    a(
        href = link,
        classes = "flex items-center w-full gap-2 px-4 py-3 rounded-pill cursor-pointer transition-all duration-300 text-sm ${if(isSelecionado) "bg-high-medium font-bold" else "bg-transparent hover:bg-high-medium font-semibold"}"
    ) {
        icone(icone, usarPadding = false, usarPreenchido = isSelecionado)
        if(isSelecionado) b { +nome }
        else span { +nome }
    }
}