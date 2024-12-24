package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.frontend.pages.restritos.inicio
import com.pscarpellini.interfaces.IEndpointInternoEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.html.respondHtml
import io.ktor.server.request.*
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.*
import kotlinx.html.FormMethod
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun Route.endpointsAbertos(
    contasRepository: ContasRepository
) {
    post(EndpointsAbertosInternoEnum.LoginRequest.pathCompleto) {
        runCatching {
            var username = ""
            var password = ""

            val parameters = call.receiveParameters()
            username = parameters["usuario"].toString()
            password = parameters["password"].toString()

            contasRepository.validarLogin(username, password).let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        val encodedErrorMessage = URLEncoder.encode(
                            "Credenciais inválidas, tente novamente.",
                            StandardCharsets.UTF_8.toString()
                        )
                        call.respondRedirect("/login?error=$encodedErrorMessage")
                    }

                    is DbResponse.Successo -> {
                        call.respondHtml(HttpStatusCode.OK) { inicio(obterSessao()) }
                    }
                }
            }
        }.onFailure {
            val encodedErrorMessage =
                URLEncoder.encode("Algo deu errado, tente novamente.", StandardCharsets.UTF_8.toString())
            call.respondRedirect("/login?error=$encodedErrorMessage")
        }

    }
}

enum class EndpointsAbertosInternoEnum(
    override val path: String,
    override val method: FormMethod,
) : IEndpointInternoEnum {
    LoginRequest("login", FormMethod.post),
}