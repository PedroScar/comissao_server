package com.pscarpellini.rotas

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.enums.produtos.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PerfisDeAcessoRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasRestritas(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    perfisDeAcessoRepository: PerfisDeAcessoRepository,
) {
    get(PaginasRestritasEnum.INTERNO.path) {
        val sessao = obterSessao()
        call.respondHtml(HttpStatusCode.OK) {
            val caminho = call.parameters["path"] ?: PaginasRestritasEnum.INICIO.path
            interno(sessao = sessao, caminho = caminho)
        }
    }

    post(PaginasRestritasEnum.INICIO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.INICIO
        sessao.paginaAtual = PaginasRestritasEnum.INICIO
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            inicio(sessao)
        }
    }

    post(PaginasComissaoEnum.PROMOCOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.PROMOCOES
        sessao.paginaAtual = PaginasComissaoEnum.PROMOCOES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            promocoes(sessao)
        }
    }

    post(PaginasComissaoEnum.SALDOS_DOS_PROMOTORES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.SALDOS_DOS_PROMOTORES
        sessao.paginaAtual = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            inicio(sessao)
        }
    }
    post(PaginasComissaoEnum.RELATORIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.RELATORIOS
        sessao.paginaAtual = PaginasComissaoEnum.RELATORIOS
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            inicio(sessao)
        }
    }

    post(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        sessao.paginaAtual = PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            gerenciamentoDeUsuarios(sessao)
        }
    }

    post(PaginasRestritasEnum.CONFIGURACOES_DO_APP.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.CONFIGURACOES_DO_APP
        sessao.paginaAtual = PaginasRestritasEnum.CONFIGURACOES_DO_APP
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            inicio(sessao)
        }
    }
    post(PaginasComissaoEnum.HISTORICO_DE_TRANSACOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.HISTORICO_DE_TRANSACOES
        sessao.paginaAtual = PaginasComissaoEnum.HISTORICO_DE_TRANSACOES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            inicio(sessao)
        }
    }

    post(PaginasRestritasEnum.MEU_PERFIL.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = null
        sessao.paginaAtual = PaginasRestritasEnum.MEU_PERFIL
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            meuPerfil(sessao)
        }
    }

    post(PaginasRestritasEnum.NOVO_USUARIO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        sessao.paginaAtual = PaginasRestritasEnum.NOVO_USUARIO
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    includeMenuPrincipal(sessao)
                    novoUsuario(sessao, perfisDeAcesso = resposta.data)
                }
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


    get(PaginasRestritasEnum.LOGOUT.path) {
        call.sessions.clear<SessaoUsuarioVO>()
        call.respondRedirect(PaginasAbertasEnum.Landing.path)
    }
}