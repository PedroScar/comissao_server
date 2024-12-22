package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.LoginRequest
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import kotlinx.html.FormMethod

fun Route.endpointsAbertos(
    contasRepository: ContasRepository
) {
    post(EndpointsAbertosEnum.LoginRequest.pathCompleto) {
        val request = call.receive<LoginRequest>()

        var username = request.usuario
        var password = request.password

        contasRepository.validarLogin(username, password).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> {
                    call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                }

                is DbResponse.Successo -> {
                    call.respond(HttpStatusCode.OK, resposta.data as ContaVO)
                }
            }
        }
    }
}

enum class EndpointsAbertosEnum(
    override val path: String,
    override val method: FormMethod,
) : IEndpointEnum {
    LoginRequest("login", FormMethod.post),
}