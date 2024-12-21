package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.routes.fragmentsRestritos
import com.pscarpellini.backend.routes.routesAbertos
import com.pscarpellini.backend.routes.routesRestritos
import com.pscarpellini.frontend.exceptions.NaoLogadoException
import com.pscarpellini.frontend.pages.geral.not_found.notFoundPage
import com.pscarpellini.frontend.style.styledRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configurePages() {
    install(StatusPages) {
        exception<NaoLogadoException> { call, cause ->
            call.respondText(text = "Você não poderia estar aqui!", status = HttpStatusCode.Forbidden)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondHtml(HttpStatusCode.NotFound) { notFoundPage() }
        }
        status(HttpStatusCode.OK) { _, _ -> }
    }

    styledRouting {
        staticResources("/static", "static")

        routesAbertos()
        routesRestritos()

        fragmentsRestritos()
    }
}
