package com.pscarpellini.frontend.routes

import com.pscarpellini.frontend.routes.enums.PagesNaoLogadasEnum
import com.pscarpellini.frontend.pages.nao_logadas.landingPage.landingPage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*

fun Routing.routesDeErros() {
    get(PagesNaoLogadasEnum.Landing.path) {
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
    get(PagesNaoLogadasEnum.Login.path) {
        call.respondHtml(HttpStatusCode.OK, PagesNaoLogadasEnum.Login.reference)
    }
}