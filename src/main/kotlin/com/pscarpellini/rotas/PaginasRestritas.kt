package com.pscarpellini.rotas

import com.pscarpellini.enums.produtos.base.CaminhosBaseEnum
import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.enums.produtos.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.produtos.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.*
import com.pscarpellini.frontend.pages.restritos.comissao.historicoDeTransacoes
import com.pscarpellini.frontend.pages.restritos.comissao.promocoes
import com.pscarpellini.frontend.pages.restritos.comissao.saldosDosPromotores
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
    get(CaminhosBaseEnum.INTERNO.path) {
        val sessao = obterSessao()
        call.respondHtml(HttpStatusCode.OK) {
            val caminho = call.parameters["path"] ?: CaminhosBaseEnum.INICIO.path
            interno(sessao = sessao, caminho = caminho)
        }
    }

    post(CaminhosBaseEnum.INICIO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.INICIO
        sessao.paginaAtual = PaginasRestritasEnum.INICIO
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            inicio(sessao)
        }
    }

    post(CaminhosComissaoEnum.PROMOCOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.PROMOCOES
        sessao.paginaAtual = PaginasComissaoEnum.PROMOCOES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            promocoes()
        }
    }

    post(CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.SALDOS_DOS_PROMOTORES
        sessao.paginaAtual = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            saldosDosPromotores()
        }
    }
    post(CaminhosComissaoEnum.RELATORIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.RELATORIOS
        sessao.paginaAtual = PaginasComissaoEnum.RELATORIOS
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            inicio(sessao)
        }
    }

    post(CaminhosBaseEnum.GERENCIAMENTO_DE_USUARIOS.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        sessao.paginaAtual = PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            gerenciamentoDeUsuarios(sessao)
        }
    }

    post(CaminhosBaseEnum.CONFIGURACOES_DO_APP.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.CONFIGURACOES_DO_APP
        sessao.paginaAtual = PaginasRestritasEnum.CONFIGURACOES_DO_APP
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            configuracoesDoApp(sessao)
        }
    }
    post(CaminhosComissaoEnum.HISTORICO_DE_TRANSACOES.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.HISTORICO_DE_TRANSACOES
        sessao.paginaAtual = PaginasComissaoEnum.HISTORICO_DE_TRANSACOES
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            historicoDeTransacoes()
        }
    }

    post(CaminhosBaseEnum.MEU_PERFIL.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = null
        sessao.paginaAtual = PaginasRestritasEnum.MEU_PERFIL
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            meuPerfil(sessao)
        }
    }

    post(CaminhosBaseEnum.NOVO_USUARIO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
        sessao.paginaAtual = PaginasRestritasEnum.NOVO_USUARIO
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao buscar perfis de acesso")
                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    includeMenuPrincipal(sessao)
                    includeHeaderLogado(sessao)
                    novoUsuario(sessao, perfisDeAcesso = resposta.data)
                }
            }
        }
    }

    post(CaminhosBaseEnum.FORMULARIO_NOVO_USUARIO.path) {
        val sessao = obterSessao()

        val parameters = call.receiveParameters()

        val nome = (parameters["nome"] ?: "").toString()
        val email = (parameters["email"] ?: "").toString()
        val telefone = (parameters["telefone"] ?: "").toString()
        val perfilDeAcesso = (parameters["perfilDeAcesso"] ?: "").toString()

        if (nome.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O campo nome deve estar preenchido")
        if (email.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O campo e-mail deve estar preenchido")
        if (perfilDeAcesso.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Selecione um perfil de acesso válido")

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
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao cadastrar usuário")
                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    includeFormNovoUsuario()
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Usuário cadastrado com sucesso")
                }
            }
        }
    }


    get(CaminhosBaseEnum.LOGOUT.path) {
        call.sessions.clear<SessaoUsuarioVO>()
        call.respondRedirect(PaginasAbertasEnum.Landing.path)
    }
}