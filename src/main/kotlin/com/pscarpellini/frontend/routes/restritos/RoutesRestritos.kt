package com.pscarpellini.frontend.routes.restritos

import com.pscarpellini.frontend.pages.restritos.homePage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Routing.routesRestritos() {
    get(RoutesRestritosEnum.Home.path) {
        call.respondHtml(HttpStatusCode.OK) { homePage() }
    }
}