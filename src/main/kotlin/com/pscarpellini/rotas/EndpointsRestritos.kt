package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointInternoEnum
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

    get(EndpointsRestritosInternoEnum.ObterClientes.pathCompleto) {
        call.respond(clientesRepository.obterClientes())
    }

    post(EndpointsRestritosInternoEnum.AdicionarCliente.pathCompleto) {
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

enum class EndpointsRestritosInternoEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointInternoEnum {
    ObterClientes("clientes", FormMethod.get),
    AdicionarCliente("adicionarCliente", FormMethod.post),
    AtualizarCliente("atualizarCliente", FormMethod.post),
    DesativarCliente("desativarCliente", FormMethod.post),

    ObterUsuarios("usuarios", FormMethod.get),
    CriarUserRequest("usuarios", FormMethod.post)
}