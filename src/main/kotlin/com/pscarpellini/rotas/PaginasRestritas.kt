package com.pscarpellini.rotas

import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeListaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.frontend.fragments.logados.promocoes.includeListaDePromocoesWidget
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.frontend.pages.restritos.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PerfisDeAcessoRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*

fun Route.paginasRestritas(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    perfisDeAcessoRepository: PerfisDeAcessoRepository,
) {
    get(PaginasRestritasEnum.INICIO.path) {
        val sessao = obterSessao()
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
    post(PaginasRestritasEnum.PROMOCOES_WIDGET.path) {
        val sessao = obterSessao()

        promocoesRepository.carregarPromocoes(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeListaDePromocoesWidget(promocoes = resposta.data) } }
            }
        }
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
        contasRepository.carregarUsuarios(sessao.conta?.cliente?.id!!).let { resposta ->
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

    get(PaginasRestritasEnum.NOVO_USUARIO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondHtml(HttpStatusCode.OK) { novoUsuario(sessao, perfisDeAcesso = resposta.data) }
            }
        }
    }
    post(PaginasRestritasEnum.NOVO_USUARIO.path) {
        val sessao = obterSessao()

        val parameters = call.receiveParameters()

        val nome = parameters["nome"].toString()
        val email = parameters["email"].toString()
        val telefone = parameters["telefone"].toString()
        val perfilDeAcesso = parameters["perfilDeAcesso"].toString()

        println("=========================================================================================")
        println("CRIANDO NOVO USUÁRIO")
        println("nome: $nome")
        println("email: $email")
        println("telefone: $telefone")
        println("perfilDeAcesso: $perfilDeAcesso")
        println("=========================================================================================")

        val novaConta = ContaVO(
            cliente = sessao.conta?.cliente!!,
            nome = nome,
            foto = "",
            endereco = "",
            cpf = "",
            email = email,
            telefone = telefone,
            saldo = 0.0,
            usuario = criarNomeDeUsuario(nome),
            status = "ATIVO",
            tipoConta = perfilDeAcesso,
        )
        contasRepository.criarUsuario(novaConta)

        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    includeFormNovoUsuario()
                    toast("Usuário criado com sucesso", tipo = TiposToastEnum.SUCCESS)
                }
            }
        }
    }
    post(PaginasRestritasEnum.FRAGMENT_SELECT_PERFIS_DE_ACESSO.path) {
        val sessao = obterSessao()
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment { includeSelectDePerfis(perfisDeAcesso = resposta.data) }
            }
        }
    }
    post(PaginasRestritasEnum.FRAGMENT_CARD_PERFIS_DE_ACESSO.path) {
        val sessao = obterSessao()
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment { includeCardDePerfis(perfisDeAcesso = resposta.data) }
            }
        }
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

    PROMOCOES_WIDGET("/int/promocoes/widget"),

    MEU_PERFIL("/int/meu_perfil"),
    PROMOCOES(ItensMenuEnum.PROMOCOES.caminho),
    SALDOS_DOS_PROMOTORES(ItensMenuEnum.SALDOS_DOS_PROMOTORES.caminho),
    RELATORIOS(ItensMenuEnum.RELATORIOS.caminho),
    GERENCIAMENTO_DE_USUARIOS(ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS.caminho),
    CONFIGURACOES_DO_APP(ItensMenuEnum.CONFIGURACOES_DO_APP.caminho),
    HISTORICO_DE_TRANSACOES(ItensMenuEnum.HISTORICO_DE_TRANSACOES.caminho),

    NOVO_USUARIO("/int/novo_usuario"),

    FRAGMENT_SELECT_PERFIS_DE_ACESSO("/int/fragment/select_perfis_de_acesso"),
    FRAGMENT_CARD_PERFIS_DE_ACESSO("/int/fragment/card_perfis_de_acesso"),

    LOGOUT("/logout"),
}