package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.endpoints.EndpointsRestritosEnum
import com.pscarpellini.backend.models.dto.requests.ClienteRequest
import com.pscarpellini.backend.models.vos.ContaVO
import com.pscarpellini.backend.models.dto.requests.ContaRequest
import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import com.pscarpellini.backend.repositories.interfaces.ContasRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.endpointsRestritos() {

    val clientesRepository: ClienteRepository by inject()
    val contasRepository: ContasRepository by inject()

    get(EndpointsRestritosEnum.ObterClientes.pathCompleto) {
        call.respond(clientesRepository.obterClientes())
    }

    post(EndpointsRestritosEnum.AdicionarCliente.pathCompleto) {
        runCatching {
            println("=======================================================================")
//            val request = call.receive<ClienteRequest>()
            println("=======================================================================")
//            println("Request recebido: $request")
            println("=======================================================================")

            val request = ClienteRequest(
                nome = "blablabla",
                endereco = "blablabla",
                cnpj = "blablabla",
                email = "blablabla",
                telefone = "blablabla",
                status = "blablabla",
            )

            clientesRepository.adicionarCliente(request)
            println("====================================================sdfsafds===================")
        }.onFailure {
            call.respond(HttpStatusCode.BadRequest, "Erro ao adicionar a conta!")
        }.onSuccess {
            call.respond(HttpStatusCode.Created, "Sucesso ao adicionar a conta!")
        }
    }






    get(EndpointsRestritosEnum.ObterUsuarios.pathCompleto) {
        call.respond(contasRepository.obterContas())
    }

    post(EndpointsRestritosEnum.CriarUserRequest.pathCompleto) {
        runCatching {
            println("=======================================================================")
            val request = call.receive<ContaRequest>()
            println("=======================================================================")
            println("Request recebido: ${request}")
            println("=======================================================================")

            val contaVO = ContaVO(
                clientId = request.clientId,
                nome = request.nome,
                endereco = request.endereco,
                cpf = request.cpf,
                email = request.email,
                telefone = request.telefone,
                usuario = request.usuario,
                senha = request.senha,
                status = request.status,
                tipoConta = request.tipoConta
            )

            contasRepository.adicionarConta(contaVO)
            println("====================================================sdfsafds===================")
        }.onFailure {
            call.respond(HttpStatusCode.BadRequest, "Erro ao adicionar a conta!")
        }.onSuccess {
            call.respond(HttpStatusCode.Created, "Sucesso ao adicionar a conta!")
        }
    }
}