package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.endpoints.EndpointsNaoLogadosEnum
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.endpointsAbertos() {

    routing {
        post(EndpointsNaoLogadosEnum.LoginRequest.path) {
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
}