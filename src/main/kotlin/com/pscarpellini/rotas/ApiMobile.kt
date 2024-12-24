package com.pscarpellini.rotas

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.LoginRequest
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.apiMobile(
    contasRepository: ContasRepository
) {
    route("/api") {
        post("/login") {
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
}
