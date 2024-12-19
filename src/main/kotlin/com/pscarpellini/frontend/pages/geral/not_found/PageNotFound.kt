package com.pscarpellini.frontend.pages.geral.not_found

import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.logo.includeLogoLumen
import com.pscarpellini.frontend.fragments.nao_logados.header_menu.includeHeaderMenu
import kotlinx.html.*

fun HTML.notFoundPage() {
    includeHtmlHeader(
//        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body(
        classes = "bg-high-light"
    ) {
        div(classes = "flex flex-col h-screen") {
            includeHeaderMenu(
                classes = "flex flex-row space-between items-center"
            ) {
                includeLogoLumen()
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