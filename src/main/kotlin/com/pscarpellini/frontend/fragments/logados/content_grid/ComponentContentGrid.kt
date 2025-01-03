package com.pscarpellini.frontend.fragments.logados.content_grid

import kotlinx.html.*

fun FlowContent.includeContentGrid(
    classes: String = "",
    linhas: Int,
    colunas: Int,
    child: FlowContent.() -> Unit
) {
    div(classes = "grid grid-rows-$linhas grid-cols-$colunas gap-6 $classes") {
        child()
    }
}