package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.h1

fun HTML.meuPerfil(
    sessao: SessaoUsuarioVO
) {
    includeHtmlHeader()
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        includeContentBodyLogado {
            includeHeaderLogado(sessao = sessao, tituloPagina = "Meu perfil", mostrarBack = false)
            includeContentGrid(
                linhas = 1,
                colunas = 1,
            ) {
                card(classes = "flex flex-col") {
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                    h1 { +"teste" }
                }
            }
        }
    }
}