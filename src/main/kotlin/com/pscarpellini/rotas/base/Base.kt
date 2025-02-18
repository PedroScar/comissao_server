package com.pscarpellini.rotas.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.*
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasAbertasEnum
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

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
    call.sessions.clear<SessaoUsuarioVO>()
    call.respondRedirect(PaginasAbertasEnum.Landing.path)
}

suspend fun RoutingContext.handleInterno() {
    val sessao = obterSessao()
    call.respondHtml(HttpStatusCode.OK) {
        val caminho = call.parameters["path"] ?: CaminhosBaseEnum.INICIO.path
        interno(sessao = sessao, caminho = caminho)
    }
}

suspend fun RoutingContext.handleConfiguracoesDoApp() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.CONFIGURACOES_DO_APP
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        configuracoesDoApp(sessao)
    }
}