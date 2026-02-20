package com.pscarpellini.plugins

import com.pscarpellini.tools.email.EmailSender
import com.pscarpellini.repositories.interfaces.*
import com.pscarpellini.rotas.apiMobile
import com.pscarpellini.rotas.endpointsAbertos
import com.pscarpellini.rotas.endpointsControle
import com.pscarpellini.rotas.endpointsRestritos
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    val extratosRepository: ExtratosRepository by inject ()
    val clientesRepository: ClienteRepository by inject()
    val contasRepository: ContasRepository by inject()
    val promocoesRepository: PromocoesRepository by inject()
    val saldosRepository: SaldosRepository by inject()
    val videosRepository: VideosRepository by inject()
    val emailSender: EmailSender by inject()

    routing {
        apiMobile(contasRepository, promocoesRepository, saldosRepository, videosRepository, extratosRepository)
        endpointsRestritos(clientesRepository)
        endpointsControle(emailSender)
        endpointsAbertos(clientesRepository,contasRepository)
    }
}
