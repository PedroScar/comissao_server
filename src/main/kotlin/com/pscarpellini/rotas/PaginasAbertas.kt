package com.pscarpellini.rotas

import com.pscarpellini.enums.PerfisDeAcessoEnum.Companion.obterEnumPeloSlug
import com.pscarpellini.enums.produtos.base.CaminhosBaseEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.redirecionarFormHTMX
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.pages.abertos.componentes.componentsPage
import com.pscarpellini.frontend.pages.abertos.landing.landingPage
import com.pscarpellini.frontend.pages.abertos.login.loginPage
import com.pscarpellini.frontend.pages.abertos.senha.esqueciMinhaSenhaPage
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasAbertas(
    contasRepository: ContasRepository
) {
    get(PaginasAbertasEnum.Landing.path) {
        runCatching { obterSessao() }
            .onSuccess { call.respondHtml(HttpStatusCode.OK) { landingPage(it) } }
            .onFailure { call.respondHtml(HttpStatusCode.OK) { landingPage() } }
    }

    get(PaginasAbertasEnum.Login.path) {
        runCatching { obterSessao() }
            .onFailure { call.respondHtml(HttpStatusCode.OK) { loginPage() } }
            .onSuccess { call.respondRedirect(CaminhosBaseEnum.INICIO.path) }
    }
    post(PaginasAbertasEnum.Login.path) {
        val parameters = call.receiveParameters()

        val username = (parameters["usuario"] ?: "").toString()
        val password = (parameters["password"] ?: "").toString()

        if (username.isEmpty()) call.respondToast(tipo = TiposToastEnum.WARNING, mensagem = "Digite o seu nome de usuário")
        if (password.isEmpty()) call.respondToast(tipo = TiposToastEnum.WARNING, mensagem = "Digite sua senha")

        contasRepository.validarLogin(username, password).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha no login")
                is DbResponse.Successo -> {
                    val tipoDeConta = resposta.data?.tipoConta ?: ""
                    val perfilDeAcesso = obterEnumPeloSlug(tipoDeConta)
                    println("Tipo de conta: $tipoDeConta")
                    println("Tipo de conta no ENUM: $perfilDeAcesso")

                    val sessao = SessaoUsuarioVO()
                    sessao.conta = resposta.data
                    sessao.papeisDeAcesso = perfilDeAcesso.papeis
                    call.sessions.set(sessao)
                    call.redirecionarFormHTMX(CaminhosBaseEnum.INICIO.path)
                }
            }
        }
    }

    get(PaginasAbertasEnum.EsqueciMinhaSenha.path) {
        call.respondHtml(HttpStatusCode.OK) { esqueciMinhaSenhaPage() }
    }

    get(PaginasAbertasEnum.Components.path) {
        call.respondHtml(HttpStatusCode.OK) { componentsPage() }
    }
}

enum class PaginasAbertasEnum(
    override val path: String
) : IPaginaEnum {
    Landing("/"),
    Login("/login"),
    EsqueciMinhaSenha("/esqueciMinhaSenha"),
    Components("/components"),
}