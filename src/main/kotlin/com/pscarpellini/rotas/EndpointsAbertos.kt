package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IEndpointEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
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
    post(EndpointsAbertosEnum.LoginRequest.pathCompleto) {
        val parameters = call.receiveParameters()
        val username = parameters["username"]
        val password = parameters["password"]

        contasRepository.validarLogin(username.toString(), password.toString()).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> {
                    val encodedErrorMessage = URLEncoder.encode("Credenciais inválidas, tente novamente.", StandardCharsets.UTF_8.toString())
                    call.respondRedirect("/?error=$encodedErrorMessage")
                }
                is DbResponse.Successo -> {

//                    call.respond<ContaVO>(resposta.data!!)
//                    call.respond(resposta.data!!)
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