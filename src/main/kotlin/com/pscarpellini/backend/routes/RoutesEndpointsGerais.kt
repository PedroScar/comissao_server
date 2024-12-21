package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.endpoints.EndpointsAbertosEnum
import com.pscarpellini.backend.enums.endpoints.EndpointsControleEnum
import com.pscarpellini.backend.models.dto.response.ServerStatus
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.management.ManagementFactory

fun Route.endpointsGerais() {
    get(EndpointsControleEnum.Ping.pathCompleto) {
        call.respond(
            HttpStatusCode.OK,
            ServerStatus(
                ip = call.request.local.remoteHost,
                uptime = ManagementFactory.getRuntimeMXBean().uptime,
                usuariosCadastrados = 0,
                clientesCadastrados = 0,
            )
        )
    }
}