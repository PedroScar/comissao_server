package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.endpoints.EndpointsRestritosEnum
import com.pscarpellini.backend.models.dto.requests.ClienteRequest
import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.endpointsRestritos(
    clientesRepository: ClienteRepository
) {

    get(EndpointsRestritosEnum.ObterClientes.pathCompleto) {
        call.respond(clientesRepository.obterClientes())
    }

    post(EndpointsRestritosEnum.AdicionarCliente.pathCompleto) {
        runCatching {
            val request = call.receive<ClienteRequest>()
            clientesRepository.adicionarCliente(request)
        }.onFailure {
            println(it.stackTraceToString())
            call.respond(HttpStatusCode.BadRequest, "Erro ao adicionar a conta!")
        }.onSuccess {
            call.respond(HttpStatusCode.Created, "Sucesso ao adicionar a conta!")
        }
    }
}