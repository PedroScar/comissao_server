package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div

fun HTML.interno(
    sessao: SessaoUsuarioVO,
    caminho: String
) {
    includeHtmlHeader()
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        div(classes = "grow flex flex-col ml-80 p-6 gap-6") {
            includeHeaderLogado(sessao = sessao)
            autoLoaderFragment(id = "conteudo-interno", usarDiferenciadorId = false, path = caminho, isVerticalLoading = true, classes = "grow flex flex-col")
        }
    }
}