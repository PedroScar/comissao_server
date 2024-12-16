package com.pscarpellini.frontend.components.logo

import kotlinx.html.*

fun FlowContent.logoLumen(
    mostrarApenasIcone: Boolean = false,
    iconeOnMobile: Boolean = true,
    clicavel: Boolean = true,
    destinoClique: String = "https://www.lumenapps.com.br/"
) {
    a(
        classes = if(!clicavel) "pointer-events-none" else "",
        href = destinoClique,
    ) {
        if(mostrarApenasIcone) {
            img(classes = " w-32 h-32", src = "/static/logo_preto.svg", alt = "Lumen Apps")
            return@a
        } else {
            img(classes = if(iconeOnMobile) "hidden lg:block" else "", src = "/static/header_lumen.svg", alt = "Lumen Apps")
            if(iconeOnMobile) {
                img(classes = "lg:hidden w-8 h-8 lg:w-auto lg:h-auto", src = "/static/logo_preto.svg", alt = "Lumen Apps")
            }
        }
    }
}