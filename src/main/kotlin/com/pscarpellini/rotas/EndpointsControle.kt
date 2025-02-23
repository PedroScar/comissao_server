package com.pscarpellini.rotas

import com.pscarpellini.email.EmailSender
import com.pscarpellini.interfaces.IEndpointInternoEnum
import com.pscarpellini.models.response.ServerStatus
import com.pscarpellini.repositories.interfaces.SaldosRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.FormMethod
import org.koin.ktor.ext.inject
import java.lang.management.ManagementFactory

fun Route.endpointsControle(
    emailSender: EmailSender
) {

    get(EndpointsControleInternoEnum.Ping.pathCompleto) {
        call.respond(
            HttpStatusCode.OK,
            ServerStatus(
                ip = call.request.local.remoteHost,
                uptime = ManagementFactory.getRuntimeMXBean().uptime,
                usuariosCadastrados = 0,
                clientesCadastrados = 0,
            )
        )
    }

    get(EndpointsControleInternoEnum.TestarEmail.pathCompleto) {
        runCatching {
            emailSender.enviarEmail(
                destinatario = "otaviolmsantos@gmail.com",
                assunto = "E-mail de teste",
                corpo = "Se você recebeu este e-mail e está lendo isso, é sinal que funcionou!",
                isHtml = false
            )
        }
            .onSuccess { call.respond(HttpStatusCode.OK, "Enviado com sucesso!") }
            .onFailure {
                call.respond(HttpStatusCode.BadRequest, """
                    Falha ao enviar!
                    -----------
                    ${it.message}
                    -----------
                    ${it.cause}
                    -----------
                    ${it.printStackTrace()}
                """.trimIndent())
            }
    }
}

enum class EndpointsControleInternoEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointInternoEnum {
    Ping("infos", FormMethod.get),
    TestarEmail("testarEmail", FormMethod.get)
}