package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import com.pscarpellini.models.response.ServerStatus
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.FormMethod
import java.lang.management.ManagementFactory

fun Route.endpointsControle() {
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

enum class EndpointsControleEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    Ping("infos", FormMethod.get)
}