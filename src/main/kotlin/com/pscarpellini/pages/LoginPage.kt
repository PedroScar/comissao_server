package com.pscarpellini.pages

import com.pscarpellini.session.Sessao
import kotlinx.html.*

fun HTML.loginPage() {
    head {
        title("Lumen Apps")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        link(
            href = "https://fonts.googleapis.com/css2?family=Nunito:wght@400;500;600;700&display=swap",
            rel = "stylesheet"
        )
    }
    body {
        div(classes = "login-header") {
            img(classes = "header-logo", src = "/static/header_lumen.svg", alt = "Lumen Apps")
            div(classes = "botoes-container-login") {
                p(classes = "texto-login-duvida") { +"Alguma dúvida?" }
                a(
                    classes = "botao-login-contato",
                    href = "https://wa.me/${Sessao.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações"
                ) {
                    attributes["target"] = "_blank"
                    +"Entre em contato"
                }
            }
        }
        div(classes = "conteudo-vertical-centralizado") {

        }
    }
}