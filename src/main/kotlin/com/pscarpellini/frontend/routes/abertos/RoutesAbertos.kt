package com.pscarpellini.frontend.routes.abertos

import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.pages.abertos.loginPage.loginPage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Routing.routesAbertos() {
    get(RoutesAbertosEnum.Landing.path) {
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
    get(RoutesAbertosEnum.Login.path) {
        call.respondHtml(HttpStatusCode.OK) { loginPage() }
    }
}