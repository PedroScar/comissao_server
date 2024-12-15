package com.pscarpellini.components.header_menu

import com.pscarpellini.AmbientController
import kotlinx.html.*

fun FlowContent.headerMenu(
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