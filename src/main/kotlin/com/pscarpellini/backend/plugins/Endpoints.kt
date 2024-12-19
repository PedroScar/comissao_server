package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.backend.repositories.interfaces.FuncionarioRepository
import com.pscarpellini.backend.repositories.interfaces.LoginRepository
import com.pscarpellini.backend.repositories.interfaces.LojaRepository
import com.pscarpellini.backend.routes.endpointsGerais
import com.pscarpellini.backend.routes.endpointsRestritos
import com.pscarpellini.backend.routes.endpointsAbertos
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    val colaboradorRepository: ColaboradorRepository by inject()
    val loginRepository: LoginRepository by inject()
    val funcionarioRepository: FuncionarioRepository by inject()
    val lojaRepository: LojaRepository by inject()

    install(ContentNegotiation) {
        json()
    }

    endpointsGerais()
    endpointsAbertos()
    endpointsRestritos()
}
