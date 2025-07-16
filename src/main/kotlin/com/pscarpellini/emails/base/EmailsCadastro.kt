package com.pscarpellini.emails.base

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.tools.email.EmailSender
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.stream.appendHTML

fun enviarEmailNovaConta(emailSender: EmailSender, novaConta: ContaVO) =
    runCatching {
        val htmlContent = buildString {
            appendHTML().html {
                includeHtmlHeader()
                body {
                    h1 (classes = "px-6 py-4 ${CoresEnum.BRAND_PURE.bg} ${CoresEnum.HIGH_LIGHT.text} ${ArredondamentosEnum.PILL}") { +"Nova conta criada - Lumen Apps" }
                    p { +"Você foi cadastrado na Lumen Apps!" }
                    br {  }
                    p { +"Para fazer login, digite o seu nome de usuário ou endereço de e-mail cadastrado e a senha abaixo:" }
                    p(classes = "px-4 py-2 ${CoresEnum.HIGH_LIGHT.bg} ${CoresEnum.BRAND_PURE.text} ${ArredondamentosEnum.PILL}") { +"${novaConta.senha}" }
                    br {  }
                    a("https://www.lumenapps.com.br/login") { +"Clique aqui para realizar o login" }
                    br {  }
                    p { +"Caso esteja com dificuldades, entre em contato!" }
                }
            }
        }
        emailSender.enviarEmail(
            destinatario = novaConta.email,
            assunto = "Nova conta criada - Lumen Apps",
            corpo = htmlContent
        )
    }

fun enviarEmailNovaSenha(emailSender: EmailSender, destinatario: String, novaSenha: String) =
    runCatching {
        val htmlContent = buildString {
            appendHTML().html {
                includeHtmlHeader()
                body {
                    h1 (classes = "px-6 py-4 ${CoresEnum.BRAND_PURE.bg} ${CoresEnum.HIGH_LIGHT.text} ${ArredondamentosEnum.PILL}") { +"Redefinição de senha - Lumen Apps" }
                    p { +"Você esqueceu sua senha e nós redefinimos para você!" }
                    br {  }
                    p { +"Para fazer login, digite o seu nome de usuário ou endereço de e-mail cadastrado e a senha:" }
                    p(classes = "px-4 py-2 ${CoresEnum.HIGH_LIGHT.bg} ${CoresEnum.BRAND_PURE.text} ${ArredondamentosEnum.PILL}") { +novaSenha }
                    br {  }
                    a("https://www.lumenapps.com.br/login") { +"Clique aqui para realizar o login" }
                    br {  }
                    p { +"Caso esteja com dificuldades, entre em contato!" }
                }
            }
        }
        emailSender.enviarEmail(
            destinatario = destinatario,
            assunto = "Redefinição de senha - Lumen Apps",
            corpo = htmlContent
        )
    }

fun enviarEmailSenhaAlterada(emailSender: EmailSender, destinatario: String) =
    runCatching {
        val htmlContent = buildString {
            appendHTML().html {
                includeHtmlHeader()
                body {
                    h1 (classes = "px-6 py-4 ${CoresEnum.BRAND_PURE.bg} ${CoresEnum.HIGH_LIGHT.text} ${ArredondamentosEnum.PILL}") { +"Alteração de senha - Lumen Apps" }
                    p { +"Você alterou sua senha!" }
                    br {  }
                    p { +"Para fazer login, digite o seu nome de usuário ou endereço de e-mail cadastrado e sua nova senha!" }
                    br {  }
                    a("https://www.lumenapps.com.br/login") { +"Clique aqui para realizar o login" }
                    br {  }
                    p { +"Caso esteja com dificuldades, entre em contato!" }
                }
            }
        }
        emailSender.enviarEmail(
            destinatario = destinatario,
            assunto = "Alteração de senha - Lumen Apps",
            corpo = htmlContent
        )
    }
