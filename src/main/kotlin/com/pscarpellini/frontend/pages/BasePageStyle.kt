package com.pscarpellini.frontend.pages

import baseHeader
import baseTexto
import com.pscarpellini.frontend.style.Colors
import gravityStart
import kotlinx.css.*
import linearLayoutHorizontal
import linearLayoutVertical
import preencherTelaRestante

fun CssBuilder.basePageStyle() {
    rule(".htmx-request") {
        put("visibility", "visible !important")
    }
}