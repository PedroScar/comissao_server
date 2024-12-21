package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import com.pscarpellini.backend.routes.endpointsAbertos
import com.pscarpellini.backend.routes.endpointsGerais
import com.pscarpellini.backend.routes.endpointsRestritos
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    install(ContentNegotiation) { json() }

    val clientesRepository: ClienteRepository by inject()

    routing {
        endpointsRestritos(clientesRepository)
        endpointsGerais()
        endpointsAbertos()
    }
}
