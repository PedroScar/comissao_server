package com.pscarpellini.backend.plugins

import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.routes.abertos.routesAbertos
import com.pscarpellini.frontend.routes.restritos.fragments.fragmentsRestritos
import com.pscarpellini.frontend.routes.restritos.routesRestritos
import com.pscarpellini.frontend.style.styledRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondHtml(HttpStatusCode.NotFound) { landingPage() }
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
