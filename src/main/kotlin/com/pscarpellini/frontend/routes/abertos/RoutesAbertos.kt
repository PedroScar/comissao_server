package com.pscarpellini.frontend.routes.abertos

import com.pscarpellini.backend.models.dto.UserSession
import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.pages.abertos.loginPage.loginPage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.h2.engine.User

fun Routing.routesAbertos() {
    get(RoutesAbertosEnum.Landing.path) {
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
    get(RoutesAbertosEnum.Login.path) {
        call.sessions.set(UserSession(nome = "Otávio Luiz"))
        call.respondHtml(HttpStatusCode.OK) { loginPage() }
    }
}