package com.pscarpellini.rotas

import com.pscarpellini.acesso.UsuariosLogados
import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PerfisDeAcessoEnum.Companion.obterEnumPeloSlug
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
    fun isUsuarioLogado(sessao: SessaoUsuarioVO): Boolean {
        return UsuariosLogados.lista.any { usuarioLogado ->
            usuarioLogado.conta?.id == sessao.conta?.id &&
                    usuarioLogado.conta?.cliente?.id == sessao.conta?.cliente?.id
        }
    }

    get(PaginasAbertasEnum.Landing.path) {
        runCatching { obterSessao() }
            .onSuccess { call.respondHtml(HttpStatusCode.OK) { landingPage(it) } }
            .onFailure { call.respondHtml(HttpStatusCode.OK) { landingPage() } }
    }

    get(PaginasAbertasEnum.Login.path) {
        runCatching { obterSessao() }
            .onFailure { call.respondHtml(HttpStatusCode.OK) { loginPage() } }
            .onSuccess { call.redirecionarFormHTMX(CaminhosBaseEnum.INICIO.path) }
    }

    post(PaginasAbertasEnum.Login.path) {
        val parameters = call.receiveParameters()

        val username = (parameters["usuario"] ?: "").toString()
        val password = (parameters["password"] ?: "").toString()

        if (username.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.WARNING,
            mensagem = "Digite o seu nome de usuário"
        )

        if (password.isEmpty()) call.respondToast(tipo = TiposToastEnum.WARNING, mensagem = "Digite sua senha")

        contasRepository.validarLogin(username, password).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha no login")
                is DbResponse.Successo -> {
                    runCatching {
                        val tipoDeConta = resposta.data?.tipoConta ?: ""
                        val perfilDeAcesso = obterEnumPeloSlug(tipoDeConta)
                        val sessao = SessaoUsuarioVO()
                        sessao.conta = resposta.data
                        sessao.papeisDeAcesso = perfilDeAcesso.papeis

                        if (!isUsuarioLogado(sessao)) { UsuariosLogados.lista.add(sessao) }

                        call.sessions.set(sessao.toCookieVO())
                    }.onFailure {
                        call.respondToast(tipo = TiposToastEnum.WARNING, mensagem = "${it.message}")
                    }.onSuccess {
                        call.redirecionarFormHTMX(CaminhosBaseEnum.INICIO.path)
                    }
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