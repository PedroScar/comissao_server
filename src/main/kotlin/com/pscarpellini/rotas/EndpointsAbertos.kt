package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.html.FormMethod
import kotlinx.serialization.Serializable

fun Route.endpointsAbertos(
    contasRepository: ContasRepository
) {
    post(EndpointsAbertosEnum.LoginRequest.pathCompleto) {
        var username = ""
        var password = ""

        runCatching {
            val parameters = call.receiveParameters()
            username = parameters["usuario"].toString()
            password = parameters["password"].toString()
        }

        if (username.isEmpty() && password.isEmpty()) {
            runCatching { call.receive<RequestData>() }
                .onSuccess {
                    username = it.usuario
                    password = it.password
                }
                .onFailure {
                    call.respond("${it.message}")
                }
        }

        contasRepository.validarLogin(username, password).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> {
                    call.respond("${resposta.mensagem}")
                }

                is DbResponse.Successo -> {
                    call.respond(HttpStatusCode.OK, resposta.data as ContaVO)
                }
            }
        }
    }
}

@Serializable
data class RequestData(val usuario: String, val password: String)

enum class EndpointsAbertosEnum(
    override val path: String,
    override val method: FormMethod,
) : IEndpointEnum {
    LoginRequest("login", FormMethod.post),
}