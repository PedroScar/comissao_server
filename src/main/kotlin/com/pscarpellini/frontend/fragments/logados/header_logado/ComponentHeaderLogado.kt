package com.pscarpellini.frontend.fragments.logados.header_logado

import kotlinx.html.*

fun FlowContent.includeHeaderLogado(
    child: FlowContent.() -> Unit
) {
    div(classes = "w-full h-20 flex items-center bg-red-300") {
        +"Olá, Carlos"
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