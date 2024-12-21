package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.html.FormMethod

fun Route.endpointsAbertos() {
    post(EndpointsAbertosEnum.LoginRequest.pathCompleto) {
        val parameters = call.receiveParameters()
        val username = parameters["username"]
        val password = parameters["password"]
//            if (loginRepository.validarLogin(username.toString(), password.toString()))
//                call.respondRedirect(PagesEnum.Home.path)
//            else {
//                val encodedErrorMessage = URLEncoder.encode(
//                    "Credenciais inválidas, tente novamente.",
//                    StandardCharsets.UTF_8.toString()
//                )
//                call.respondRedirect("/?error=$encodedErrorMessage")
//            }
    }
}

enum class EndpointsAbertosEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    LoginRequest("login", FormMethod.post),
}