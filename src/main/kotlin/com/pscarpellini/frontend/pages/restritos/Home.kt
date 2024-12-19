package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import kotlinx.html.*

fun HTML.homePage() {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal()
        includeContentBodyLogado {
            includeHeaderLogado {}
            includeContentGrid(
                linhas = 2,
                colunas = 3,
                classes = "h-full"
            ) {
                div(classes = "rounded-lg col-span-2 bg-high-pure") {

                }
                div(classes = "rounded-lg bg-high-pure") {

                }
                div(classes = "rounded-lg col-span-3 bg-high-pure") {

                }
            }
        }
    }
}