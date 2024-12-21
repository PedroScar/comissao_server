package com.pscarpellini.plugins

import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.rotas.endpointsAbertos
import com.pscarpellini.rotas.endpointsControle
import com.pscarpellini.rotas.endpointsRestritos
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
        endpointsControle()
        endpointsAbertos()
    }
}
