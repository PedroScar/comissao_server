package com.pscarpellini.plugins

import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.repositories.interfaces.VideosRepository
import com.pscarpellini.rotas.apiMobile
import com.pscarpellini.rotas.endpointsAbertos
import com.pscarpellini.rotas.endpointsControle
import com.pscarpellini.rotas.endpointsRestritos
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    val clientesRepository: ClienteRepository by inject()
    val contasRepository: ContasRepository by inject()
    val promocoesRepository: PromocoesRepository by inject()
    val videosRepository: VideosRepository by inject()

    routing {
        apiMobile(contasRepository, promocoesRepository, videosRepository)
        endpointsRestritos(clientesRepository)
        endpointsControle()
        endpointsAbertos(contasRepository)
    }
}
