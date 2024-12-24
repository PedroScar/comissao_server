package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointMobileEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.LoginRequest
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import kotlinx.html.FormMethod

fun Route.apiMobile(
    contasRepository: ContasRepository
) {
    post(ApiMobileEnum.LoginRequest.path) {
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

enum class ApiMobileEnum(
    override val path: String,
    override val method: FormMethod,
) : IEndpointMobileEnum{
    LoginRequest("login", FormMethod.post)
}
