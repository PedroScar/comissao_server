package com.pscarpellini.backend.plugins

import com.pscarpellini.frontend.routes.enums.PagesNaoLogadasEnum
import com.pscarpellini.frontend.routes.routesLogadas
import com.pscarpellini.frontend.routes.routesNaoLogadas
import com.pscarpellini.frontend.style.styledRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.body
import kotlinx.css.title
import kotlinx.html.title

fun Application.configurePages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondHtml(HttpStatusCode.NotFound, PagesNaoLogadasEnum.NOT_FOUND_404.reference)
        }
        status(HttpStatusCode.OK) { _, _ -> }
    }

    styledRouting {
        staticResources("/static", "static")

        routesNaoLogadas()
        routesLogadas()
    }
}
