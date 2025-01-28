package com.pscarpellini.rotas

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.LoginRequest
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.repositories.interfaces.VideosRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.apiMobile(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    videosRepository: VideosRepository
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

        get("/promocoes") {
            val clientId = call.request.queryParameters["clienteId"]?.toIntOrNull() ?: 0

            promocoesRepository.carregarPromocoes(clienteId = clientId).let {  resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                    }

                    is DbResponse.Successo -> {
                        call.respond(HttpStatusCode.OK, resposta.data as List<PromocaoVO>)
                    }
                }
            }
        }

        get("/videos") {
            val clientId = call.request.queryParameters["clienteId"]?.toIntOrNull() ?: 0

            videosRepository.carregarVideos(clienteId = clientId).let {  resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                    }

                    is DbResponse.Successo -> {
                        call.respond(HttpStatusCode.OK, resposta.data as List<VideoVO>)
                    }
                }
            }
        }
    }
}