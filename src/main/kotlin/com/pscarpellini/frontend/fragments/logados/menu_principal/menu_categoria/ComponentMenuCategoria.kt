package com.pscarpellini.frontend.fragments.logados.menu_principal.menu_categoria

import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeMenuCategoria(
    nome: String,
) {
    div("flex items-center w-full px-4 pt-6") {
        span("text-low-light font-semibold text-sm uppercase") {
            +nome
        }

        div("flex-grow border-t border-high-medium ml-4") {}
    }
}