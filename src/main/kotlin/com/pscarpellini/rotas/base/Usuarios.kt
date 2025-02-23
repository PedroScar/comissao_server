package com.pscarpellini.rotas.base

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeTabelaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.tools.image.saveImageToPublicFolder
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.jvm.javaio.*

suspend fun RoutingContext.handleFragmentTabelaUsuarios(contasRepository: ContasRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""

    val sessao = obterSessao()
    contasRepository.carregarUsuarios(nome = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
            is DbResponse.Successo -> {
                call.respondFragment { includeTabelaDeUsuarios(contas = resposta.data) }
            }
        }
    }
}

suspend fun RoutingContext.handleGerenciamentoDeUsuarios() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        gerenciamentoDeUsuarios(sessao)
    }
}

suspend fun RoutingContext.handleMeuPerfil() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.MEU_PERFIL
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        meuPerfil(sessao)
    }
}

suspend fun RoutingContext.handleEditarMeuPerfil() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.EDITAR_MEU_PERFIL
    call.respondFragment(HttpStatusCode.OK) { editarMeuPerfil(sessao) }
}

suspend fun RoutingContext.handleFormularioEditarMeuPerfil(
    contasRepository: ContasRepository
) {
    val sessao = obterSessao()

    val multipart = call.receiveMultipart(formFieldLimit = 10_000_000)

    var telefone = ""
    var imagemDePerfil = ""

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "telefone" -> telefone = part.value
                }
            }

            is PartData.FileItem -> {
//                val fileBytes = part.provider().toByteArray()
//                imagemDePerfil = Base64.getEncoder().encodeToString(fileBytes)
                imagemDePerfil = saveImageToPublicFolder(part.provider().toInputStream(), 200)
            }

            else -> Unit
        }
        part.dispose()
    }

    contasRepository.atualizarMeuPerfil(usuarioId = sessao.conta?.id ?: -1, telefone = telefone, imagemDePerfil = imagemDePerfil)

    call.respondFragment(HttpStatusCode.OK) {
        meuPerfil(sessao)
        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Informações salvas com sucesso!")
    }
}

suspend fun RoutingContext.handleNovoUsuario() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.NOVO_USUARIO
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        novoUsuario(sessao)
    }
}

suspend fun RoutingContext.handleFormularioNovoUsuario(
    contasRepository: ContasRepository
) {
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
        usuario = criarNomeDeUsuario(nome),
        status = "ATIVO",
        tipoConta = perfilDeAcesso,
        imagemDePerfil = ""
    )
    contasRepository.criarUsuario(novaConta)

    call.respondFragment(HttpStatusCode.OK) {
        includeFormNovoUsuario()
        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Usuário cadastrado com sucesso")
    }
}