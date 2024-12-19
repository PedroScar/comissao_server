package com.pscarpellini.frontend.fragments.logados.content_body

import kotlinx.html.*

fun FlowContent.includeContentBodyLogado(
    child: FlowContent.() -> Unit
) {
    div(classes = "grow flex flex-col p-6 gap-6") {
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