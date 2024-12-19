package com.pscarpellini.frontend.fragments.logados.menu_principal.menu_item

import kotlinx.html.FlowContent
import kotlinx.html.a

fun FlowContent.includeMenuItem(
    nome: String,
    icone: String,
    link: String,
) {
    a(
        href = link,
        classes = "flex items-center w-full gap-2 px-4 py-3 rounded-full hover:bg-high-medium cursor-pointer font-bold transition-all duration-300 text-sm"
    ) {
        +icone
        +nome
    }
}