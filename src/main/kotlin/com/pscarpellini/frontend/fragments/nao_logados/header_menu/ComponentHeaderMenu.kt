package com.pscarpellini.frontend.fragments.nao_logados.header_menu

import kotlinx.html.*

fun FlowContent.includeHeaderMenu(
    id: String = "",
    classes: String,
    child: FlowContent.() -> Unit
) {
    div(classes = "w-full flex justify-center") {
        attributes["id"] = id
        div(classes = "py-8 px-6 lg:px-12 w-full justify-between $classes") {
            child()
        }
    }
}