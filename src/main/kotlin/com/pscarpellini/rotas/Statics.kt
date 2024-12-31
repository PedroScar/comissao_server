package com.pscarpellini.rotas

import io.ktor.server.application.Application
import io.ktor.server.http.content.staticFiles
import io.ktor.server.routing.*
import java.io.File

fun Application.configureStatics() {
    routing {
        staticFiles("/images", File("files"))
    }
}
