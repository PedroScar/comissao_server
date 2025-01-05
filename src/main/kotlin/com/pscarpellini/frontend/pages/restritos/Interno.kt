package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.toast.toastContainer
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
        toastContainer()
        includeMenuPrincipal(sessao)
        div(classes = "grow flex flex-col ml-80 p-6 gap-6") {
            attributes["id"] = "conteudo-interno"
//            inicio(sessao)
            autoLoaderFragment(id = "conteudo-interno", usarDiferenciadorId = false, path = caminho, isVerticalLoading = true, hxReplaceUrl = caminho)
        }
//        includeContentBodyLogado {
//            autoLoaderFragment(id = "conteudo", path = PaginasRestritasEnum.INICIO.path, isVerticalLoading = true)

//        }
    }
}