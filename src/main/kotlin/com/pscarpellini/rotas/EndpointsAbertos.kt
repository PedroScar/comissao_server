package com.pscarpellini.rotas

import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.server.request.*
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.*
import kotlinx.html.FormMethod
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun Route.endpointsAbertos(
    contasRepository: ContasRepository
) {
    route("") {
        post(EndpointsAbertosInternoEnum.LoginRequest.path) {
            runCatching {
                val parameters = call.receiveParameters()

                var username = parameters["usuario"].toString()
                var password = parameters["password"].toString()

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
                            call.respondRedirect("/int/inicio")
//                            call.respondHtml(HttpStatusCode.OK) { inicio(obterSessao()) }
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
}

enum class EndpointsAbertosInternoEnum(
    val path: String,
    val method: FormMethod,
) {
    LoginRequest("login", FormMethod.post),
}