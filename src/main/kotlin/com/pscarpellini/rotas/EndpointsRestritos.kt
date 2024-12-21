package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.repositories.interfaces.ClienteRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.FormMethod

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

enum class EndpointsRestritosEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    LoginRequest("login", FormMethod.post),

    ObterClientes("clientes", FormMethod.get),
    AdicionarCliente("adicionarCliente", FormMethod.post),
    AtualizarCliente("atualizarCliente", FormMethod.post),
    DesativarCliente("desativarCliente", FormMethod.post),

    ObterUsuarios("usuarios", FormMethod.get),
    CriarUserRequest("usuarios", FormMethod.post)
}