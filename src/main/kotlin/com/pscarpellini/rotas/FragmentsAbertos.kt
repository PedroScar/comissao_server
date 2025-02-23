package com.pscarpellini.rotas

import com.pscarpellini.email.EmailSender
import com.pscarpellini.extensions.gerarSenhaBasica
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

fun Route.fragmentsAbertos(
    emailSender: EmailSender,
    contasRepository: ContasRepository,
) {
    post(FragmentsAbertosEnum.FORMULARIO_REDEFINIR_SENHA.path) {
        val parameters = call.receiveParameters()

        val email = (parameters["email"] ?: "").toString()

        if (email.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O campo email deve estar preenchido")
        else {
            runCatching { contasRepository.validarEmailEsqueciMinhaSenha(email) }
                .onSuccess {
                    if(it) {
                        val novaSenhaAleatoria = gerarSenhaBasica()
                        contasRepository.definirSenhaProvisoria(email = email, novaSenha = novaSenhaAleatoria)
                        runCatching {
                            val htmlContent = buildString {
                                appendHTML().html {
                                    includeHtmlHeader()
                                    body {
                                        h1 (classes = "px-6 py-4 ${CoresEnum.BRAND_PURE.bg} ${CoresEnum.HIGH_LIGHT.text} ${ArredondamentosEnum.PILL}") { +"Redefinição de senha" }
                                        p { +"Você esqueceu sua senha e nós redefinimos para você!" }
                                        br {  }
                                        p { +"Para fazer login, digite o seu nome de usuário ou endereço de e-mail cadastrado e a senha:" }
                                        p(classes = "px-4 py-2 ${CoresEnum.HIGH_LIGHT.bg} ${CoresEnum.BRAND_PURE.text} ${ArredondamentosEnum.PILL}") { +novaSenhaAleatoria }
                                        br {  }
                                        p { +"Caso esteja com dificuldades, entre em contato!" }
                                    }
                                }
                            }
                            emailSender.enviarEmail(
                                destinatario = "otaviolmsantos@gmail.com",
//                                destinatario = email,
                                assunto = "Redefinição de senha",
                                corpo = htmlContent
                            )
                        }
                            .onSuccess { call.respondToast(tipo = TiposToastEnum.SUCCESS, mensagem = "Enviamos um e-mail com uma senha provisória - $novaSenhaAleatoria") }
                            .onFailure { call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Ocorreu um erro ao enviar o e-mail com a nova senha") }
                    } else call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Enviamos um e-mail com uma senha provisória para o e-mail digitado 1")
                }
                .onFailure { call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Enviamos um e-mail com uma senha provisória para o e-mail digitado 2") }

        }
    }
}

enum class FragmentsAbertosEnum(
    override val path: String,
): IFragmentEnum {
    //    FRAGMENTS ISOLADOS
    FORMULARIO_REDEFINIR_SENHA("/forms/redefinir_senha"),
}