package com.pscarpellini.rotas

import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.redirecionarFormHTMX
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeListaDeUsuarios
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.frontend.pages.abertos.landing.landingPage
import com.pscarpellini.frontend.pages.restritos.gerenciamentoDeUsuarios
import com.pscarpellini.frontend.pages.restritos.inicio
import com.pscarpellini.frontend.pages.restritos.meuPerfil
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasRestritas(
    contasRepository: ContasRepository
) {
    get(PaginasRestritasEnum.INICIO.path) {
        val sessao = obterSessao()
        if(sessao.cliente == null) println("================================================= CLIENTE DA SESSÃO NULO")
        sessao.menuSelecionado = ItensMenuEnum.INICIO
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }

    get(PaginasRestritasEnum.MEU_PERFIL.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.INICIO
        call.respondHtml(HttpStatusCode.OK) { meuPerfil(sessao) }
    }

    get(PaginasRestritasEnum.PROMOCOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.PROMOCOES
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }
    get(PaginasRestritasEnum.SALDOS_DOS_PROMOTORES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.SALDOS_DOS_PROMOTORES
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }
    get(PaginasRestritasEnum.RELATORIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.RELATORIOS
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }

    get(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        call.respondHtml(HttpStatusCode.OK) { gerenciamentoDeUsuarios(sessao) }
    }
    post(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS

        contasRepository.carregarUsuarios(sessao.cliente?.clientId!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeListaDeUsuarios(contas = resposta.data) } }
            }
        }
    }

    get(PaginasRestritasEnum.CONFIGURACOES_DO_APP.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.CONFIGURACOES_DO_APP
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }
    get(PaginasRestritasEnum.HISTORICO_DE_TRANSACOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.HISTORICO_DE_TRANSACOES
        call.respondHtml(HttpStatusCode.OK) { inicio(sessao) }
    }









    get(PaginasRestritasEnum.LOGOUT.path) {
        call.sessions.clear<SessaoUsuarioVO>()
        call.respondRedirect(PaginasAbertasEnum.Landing.path)
    }
}

enum class PaginasRestritasEnum(
    override val path: String
): IPaginaEnum {
    INICIO(ItensMenuEnum.INICIO.caminho),
    MEU_PERFIL("/int/meu_perfil"),
    PROMOCOES(ItensMenuEnum.PROMOCOES.caminho),
    SALDOS_DOS_PROMOTORES(ItensMenuEnum.SALDOS_DOS_PROMOTORES.caminho),
    RELATORIOS(ItensMenuEnum.RELATORIOS.caminho),
    GERENCIAMENTO_DE_USUARIOS(ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS.caminho),
    CONFIGURACOES_DO_APP(ItensMenuEnum.CONFIGURACOES_DO_APP.caminho),
    HISTORICO_DE_TRANSACOES(ItensMenuEnum.HISTORICO_DE_TRANSACOES.caminho),

    LOGOUT("/logout"),
}