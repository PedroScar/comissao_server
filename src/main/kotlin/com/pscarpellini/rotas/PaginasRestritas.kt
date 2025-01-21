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
import com.pscarpellini.frontend.pages.restritos.comissao.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.utils.io.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Base64

fun Route.paginasRestritas(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
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
    post(CaminhosComissaoEnum.NOVA_PROMOCAO.path) {
        val sessao = obterSessao()
        sessao.menuSelecionado = ItensMenuEnum.PROMOCOES
        sessao.paginaAtual = PaginasComissaoEnum.NOVA_PROMOCAO
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            novaPromocao(sessao)
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
        call.respondFragment(HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
            includeHeaderLogado(sessao)
            novoUsuario(sessao)
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

        call.respondFragment(HttpStatusCode.OK) {
            includeFormNovoUsuario()
            toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Usuário cadastrado com sucesso")
        }
    }


    post(CaminhosComissaoEnum.FORMULARIO_NOVA_PROMOCAO.path) {
        val sessao = obterSessao()

        val multipart = call.receiveMultipart()

        var nome = ""
        var descricao = ""
        var dataDeInicio = ""
        var dataDeEncerramento = ""
        var imagemDeExibicao = ""
        var precoDeExibicao = ""
        var valorAnterior = ""
        var valorAtual = ""

        multipart.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    // Processa campos de texto
                    when (part.name) {
                        "nome" -> nome = part.value
                        "descricao" -> descricao = part.value
                        "data_de_inicio" -> dataDeInicio = part.value
                        "data_de_encerramento" -> dataDeEncerramento = part.value
                        "preco_de_exibicao" -> precoDeExibicao = part.value
                        "valor_anterior" -> valorAnterior = part.value
                        "valor_atual" -> valorAtual = part.value
                    }
                }
                is PartData.FileItem -> {
                    val fileBytes = part.provider().toByteArray()
                    imagemDeExibicao = Base64.getEncoder().encodeToString(fileBytes)
                }
                else -> Unit
            }

            part.dispose()
        }

        val novaPromocao = PromocaoVO(
            clientId = sessao.conta?.cliente?.id ?: -1,
            titulo = nome,
            subtitulo = descricao,
            conteudo = "",
            imagem = imagemDeExibicao,
            dataValidade = LocalDate.parse(dataDeEncerramento).atTime(23, 59),
            dataCriacao = LocalDateTime.now(),
            dataVisivel = LocalDate.parse(dataDeInicio).atTime(0, 0),
            dataDisponivel = LocalDate.parse(dataDeInicio).atTime(0, 0),
            status = "INATIVA",
            duracaoIndeterminada = false,
        )
        promocoesRepository.criarPromocao(novaPromocao)

        call.respondFragment(HttpStatusCode.OK) {
            includeFormNovaPromocao()
            toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Promoção cadastrada com sucesso")
        }
    }


    get(CaminhosBaseEnum.LOGOUT.path) {
        call.sessions.clear<SessaoUsuarioVO>()
        call.respondRedirect(PaginasAbertasEnum.Landing.path)
    }
}