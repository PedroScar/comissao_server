package com.pscarpellini.rotas.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.extensions.fecharSessao
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.inicio
import com.pscarpellini.frontend.pages.restritos.base.interno
import com.pscarpellini.rotas.PaginasAbertasEnum
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleInicio() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.INICIO
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        inicio(sessao)
    }
}

suspend fun RoutingContext.handleLogout() {
    fecharSessao()
    call.respondRedirect(PaginasAbertasEnum.Landing.path)
}

suspend fun RoutingContext.handleInterno() {
    val sessao = obterSessao()
    call.respondHtml(HttpStatusCode.OK) {
        val caminho = call.parameters["path"] ?: CaminhosBaseEnum.INICIO.path
        interno(sessao = sessao, caminho = caminho)
    }
}