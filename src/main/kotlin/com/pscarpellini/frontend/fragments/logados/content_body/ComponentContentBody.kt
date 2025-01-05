package com.pscarpellini.frontend.fragments.logados.content_body

import kotlinx.html.*

fun FlowContent.includeContentBodyLogado(
    child: FlowContent.() -> Unit
) {
    div(classes = "grow flex flex-col ml-80 p-6") {
        attributes["id"] = "conteudo-interno"
        child()
    }
}