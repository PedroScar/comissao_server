package com.pscarpellini.rotas

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.LoginRequest
import com.pscarpellini.models.response.ExtratoMobile
import com.pscarpellini.models.response.VideosPaginacao
import com.pscarpellini.models.tableModels.SaldoDB
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.apiMobile(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    saldosRepository: SaldosRepository,
    videosRepository: VideosRepository,
    extratosRepository: ExtratosRepository
) {
    route("/api") {
        post("/login") {
            val request = call.receive<LoginRequest>()

            val username = request.usuario
            val password = request.password

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

            promocoesRepository.carregarPromocoes(clienteId = clientId).let { resposta ->
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

        get("/videosDestaque") {
            val clientId = call.request.queryParameters["clienteId"]?.toIntOrNull() ?: 0

            videosRepository.carregarVideosDestaque(clienteId = clientId).let { resposta ->
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

        get("/videosPaginacao") {
            val clientId = call.request.queryParameters["clienteId"]?.toIntOrNull() ?: 0
            val pagina = call.request.queryParameters["pagina"]?.toIntOrNull() ?: 0

            videosRepository.carregarVideosPaginacao(clienteId = clientId, pagina = pagina).let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                    }

                    is DbResponse.Successo -> {
                        call.respond(HttpStatusCode.OK, resposta.data as VideosPaginacao)
                    }
                }
            }
        }

        get("/saldoAtual") {
            val contaId = call.request.queryParameters["contaId"]?.toIntOrNull() ?: 0

            saldosRepository.carregarSaldoContaAPI(contaId = contaId).let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                    }

                    is DbResponse.Successo -> {
                        call.respond(HttpStatusCode.OK, resposta.data as SaldoDB)
                    }
                }
            }
        }

        get("/extrato") {
            val contaId = call.request.queryParameters["contaId"]?.toIntOrNull() ?: 0

            extratosRepository.carregarExtratosRecentes(contaId).let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> {
                        call.respond(HttpStatusCode.ServiceUnavailable, "${resposta.mensagem}")
                    }

                    is DbResponse.Successo -> {
                        val listaExtratoMobile: List<ExtratoMobile> = resposta.data?.map { dados ->
                            ExtratoMobile(
                                promocaoId = dados.promocao?.id ?: 0,
                                dataCriacao = dados.dataCriacao ?: LocalDateTime.now(),
                                valor = dados.valor,
                                isCredito = dados.isCredito
                            )
                        } ?: emptyList()

                        call.respond(HttpStatusCode.OK, listaExtratoMobile)
                    }
                }
            }
        }
    }
}