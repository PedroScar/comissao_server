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




//img(classes = "header-logo", src = "/static/header_lumen.svg", alt = "Lumen Apps")
//div(classes = "linearLayoutHorizontal-login") {
//    p(classes = "texto-login-duvida") { +"Alguma dúvida?" }
//    a(
//        classes = "botao-login-contato",
//        href = "https://wa.me/${AmbientController.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações"
//    ) {
//        attributes["target"] = "_blank"
//        +"Entre em contato"
//    }
//}