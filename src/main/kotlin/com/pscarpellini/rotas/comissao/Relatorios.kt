package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.inicio
import io.ktor.http.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleRelatorios() {
    val sessao = obterSessao()
    sessao.menuSelecionado = ItensMenuEnum.RELATORIOS
    sessao.paginaAtual = PaginasComissaoEnum.RELATORIOS
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        inicio(sessao)
    }
}