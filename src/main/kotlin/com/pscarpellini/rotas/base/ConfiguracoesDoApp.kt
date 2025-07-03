package com.pscarpellini.rotas.base

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.extensions.adicionarToastNaResposta
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.redirecionarFormHTMX
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.editarConfiguracoesDoApp
import com.pscarpellini.frontend.pages.restritos.base.exibirConfiguracoesDoApp
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ClienteRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import java.util.*

private suspend fun RoutingContext.devolverExibirConfiguracoesDoApp(
    sessao: SessaoUsuarioVO,
    cliente: ClienteVO
) {
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        exibirConfiguracoesDoApp(sessao = sessao, cliente = cliente)
    }
}

suspend fun RoutingContext.handleExibirConfiguracoesDoApp(
    clienteRepository: ClienteRepository
) {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.EXIBIR_CONFIGURACOES_DO_APP

    clienteRepository.carregarCliente(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o cliente selecionado"
            )

            is DbResponse.Successo -> {
                if (resposta.data == null) {
                    call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o cliente selecionado"
                    )
                } else {
                    devolverExibirConfiguracoesDoApp(sessao = sessao, cliente = resposta.data)
                }
            }
        }
    }
}

suspend fun RoutingContext.handleEditarConfiguracoesDoApp(
    clienteRepository: ClienteRepository
) {
    val sessao = obterSessao()

    clienteRepository.carregarCliente(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o cliente selecionado"
            )

            is DbResponse.Successo -> {
                if (resposta.data == null) {
                    call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o cliente selecionado"
                    )
                } else {
                    call.respondFragment(HttpStatusCode.OK) {
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        editarConfiguracoesDoApp(sessao)
                    }
                }
            }
        }
    }
}

suspend fun RoutingContext.handleFormularioEditarConfiguracoesApp(
    clienteRepository: ClienteRepository
) {
    val sessao = obterSessao()
    val multipart = call.receiveMultipart()

    var clienteId: Int? = null
    var nome = ""
    var emailContato = ""
    var endereco = ""
    var telefone = ""
    var cnpj = ""
    var logo = ""
    var corPreferida = ""

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "clienteId" -> clienteId = part.value.toIntOrNull()
                    "nome" -> nome = part.value
                    "emailContato" -> emailContato = part.value
                    "endereco" -> endereco = part.value
                    "telefone" -> telefone = part.value
                    "cnpj" -> cnpj = part.value
                    "corPreferida" -> corPreferida = part.value
                }
            }

            is PartData.FileItem -> {
                if (part.name == "logo" && part.originalFileName != null && part.originalFileName!!.isNotBlank()) {
                    val fileBytes = part.provider().toByteArray()
                    logo = Base64.getEncoder().encodeToString(fileBytes)
                }
            }

            else -> Unit
        }
        part.dispose()
    }

    clienteRepository.carregarCliente(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> Unit
            is DbResponse.Successo -> {
                resposta.data?.logo?.let {
                    if (logo.isBlank()) logo = it
                }
            }
        }
    }

    if (nome.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "O nome precisa estar preenchido"
    )

    if (cnpj.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "O CNPJ precisa estar preenchido"
    )

    if (emailContato.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Deve ter um endereço de e-mail para contato"
    )

    if (logo.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Envie uma imagem de logo"
    )

    val clienteEditado = ClienteVO(
        id = clienteId,
        nome = nome,
        endereco = endereco,
        cnpj = cnpj,
        email = emailContato,
        telefone = telefone,
        status = "",
        logo = logo,
    )

    clienteRepository.editarCliente(clienteEditado).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> {
                call.respondFragment(HttpStatusCode.OK) {
                    exibirConfiguracoesDoApp(sessao, sessao.conta?.cliente!!)
                    toast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ops... algo errado aconteceu!")
                }
            }

            is DbResponse.Successo -> {
                sessao.conta?.cliente = resposta.data
                call.respondFragment(HttpStatusCode.OK) {
                    exibirConfiguracoesDoApp(sessao, sessao.conta?.cliente!!)
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Configurações do App alteradas com sucesso!")
                }
            }
        }
    }
}