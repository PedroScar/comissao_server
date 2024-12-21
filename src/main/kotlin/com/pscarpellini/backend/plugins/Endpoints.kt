package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.backend.repositories.interfaces.ContasRepository
import com.pscarpellini.backend.repositories.interfaces.LoginRepository
import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import com.pscarpellini.backend.routes.endpointsGerais
import com.pscarpellini.backend.routes.endpointsRestritos
import com.pscarpellini.backend.routes.endpointsAbertos
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    val colaboradorRepository: ColaboradorRepository by inject()
    val loginRepository: LoginRepository by inject()
    val funcionarioRepository: ContasRepository by inject()
    val lojaRepository: ClienteRepository by inject()

    install(ContentNegotiation) { json() }

    routing {
        endpointsRestritos()
        endpointsGerais()
        endpointsAbertos()
    }
}
