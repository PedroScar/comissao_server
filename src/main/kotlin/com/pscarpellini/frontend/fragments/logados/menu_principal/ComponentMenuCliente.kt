package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

fun FlowContent.includeMenuCliente(
    nome: String,
    classes: String = "",
) {
    div("flex flex-row items-center font-bold text-lg mb-4 gap-2 rounded-pill border border-high-medium px-2 py-2 $classes") {
        img(src = "/static/cliente_exemplo.png", classes = "rounded-pill size-8")
        +nome
    }
}