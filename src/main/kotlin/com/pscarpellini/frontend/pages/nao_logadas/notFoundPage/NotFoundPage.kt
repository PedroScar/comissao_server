package com.pscarpellini.frontend.pages.nao_logadas.notFoundPage

import com.pscarpellini.frontend.components.header_menu.headerMenu
import com.pscarpellini.frontend.components.html_header.htmlHeader
import com.pscarpellini.frontend.components.logo.logoLumen
import kotlinx.html.*

fun HTML.notFoundPage() {
    htmlHeader(
//        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body(
        classes = "bg-high-light"
    ) {
        div(classes = "flex flex-col h-screen") {
            headerMenu(
                classes = "flex flex-row space-between items-center"
            ) {
                logoLumen()
            }
            div(classes = "flex flex-row flex-grow rounded-3xl bg-high-pure mx-6 lg:mx-12 mb-6 lg:mb-12") {
                div(classes = "flex-grow flex flex-row lg:items-center justify-center px-6 py-4") {
                    div(classes = "flex flex-col") {
                        h1 {
                            +"Ooops, a página que você procura, sumiu!"
                        }
                        a("https://www.lumenapps.com.br/") {
                            +"Voltar ao início"
                        }
                    }
                }
            }
        }
    }
}