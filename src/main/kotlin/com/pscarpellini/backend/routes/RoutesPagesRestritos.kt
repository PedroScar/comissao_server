package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.routes.PagesRestritosEnum
import com.pscarpellini.backend.models.dto.UserSession
import com.pscarpellini.frontend.extensions.obterSessao
import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.pages.restritos.homePage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Routing.routesRestritos() {
    get(PagesRestritosEnum.Home.path) {
        val sessao = obterSessao()
        call.respondHtml(HttpStatusCode.OK) { homePage(sessao) }
    }
    get(PagesRestritosEnum.Logout.path) {
        call.sessions.clear<UserSession>()
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
}