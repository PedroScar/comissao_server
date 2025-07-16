package com.pscarpellini.rotas

import com.pscarpellini.emails.base.enviarEmailNovaSenha
import com.pscarpellini.tools.email.EmailSender
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

fun Route.formRedefinirSenha(
    emailSender: EmailSender,
    contasRepository: ContasRepository,
) {
    post(FragmentsAbertosEnum.FORMULARIO_REDEFINIR_SENHA.path) {
        val parameters = call.receiveParameters()

        val usuarioOuEmail = (parameters["email"] ?: "").toString()

        if (usuarioOuEmail.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O campo email deve estar preenchido")
        else {
            runCatching { contasRepository.validarEmailEsqueciMinhaSenha(usuarioOuEmail) }
                .onSuccess {
                    if(it.second) {
                        val novaSenhaAleatoria = gerarSenhaBasica()
                        contasRepository.definirSenhaProvisoria(email = it.first, novaSenha = novaSenhaAleatoria)
                        enviarEmailNovaSenha(emailSender, destinatario = it.first, novaSenha = novaSenhaAleatoria)
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