package com.pscarpellini.frontend.fragments.logados.content_grid

import kotlinx.html.*

fun FlowContent.includeContentGrid(
    classes: String = "",
    linhas: Int? = null,
    colunas: Int? = null,
    child: FlowContent.() -> Unit
) {
    div(classes = "grid ${if(linhas != null) "grid-rows-$linhas" else ""} ${if(colunas != null) "grid-cols-$colunas" else ""} gap-6 $classes") {
        child()
    }
}