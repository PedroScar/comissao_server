package com.pscarpellini.rotas.comissao

import com.pscarpellini.emails.base.enviarEmailNovaConta
import com.pscarpellini.emails.base.enviarEmailSenhaAlterada
import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.gerarSenhaBasica
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeTabelaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.tools.email.EmailSender
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.html
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.stream.appendHTML
import java.util.*
import kotlin.text.isEmpty

suspend fun RoutingContext.handleFragmentTabelaUsuarios(contasRepository: ContasRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""

    val sessao = obterSessao()
    contasRepository.carregarUsuarios(nome = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
            is DbResponse.Successo -> call.respondFragment { includeTabelaDeUsuarios(contas = resposta.data, sessao = sessao) }
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
                val fileBytes = part.provider().toByteArray()
                imagemDePerfil = Base64.getEncoder().encodeToString(fileBytes)
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

suspend fun RoutingContext.handleMeuPerfilAlterarSenha() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasRestritasEnum.MEU_PERFIL
    call.respondFragment(HttpStatusCode.OK) {
        formulario(id = "form-alterar-senha-meu-perfil", autoValidar = true) {
            inputField(
                label = "Nova senha",
                inputType = InputType.password,
                nomeDoCampo = "password",
                hint = "Digite uma nova senha segura"
            )
            botao(
                tipo = TiposBotaoEnum.NEUTRAL,
                hxPath = CaminhosBaseEnum.FORMULARIO_ALTERAR_SENHA_PERFIL,
                classes = "self-start mt-2",
                hxTarget = "form-perfil-alterar-senha"
            ) { +"Alterar senha" }
        }
    }
}

suspend fun RoutingContext.handleFormularioMeuPerfilAlterarSenha(
    contasRepository: ContasRepository,
    emailSender: EmailSender
) {
    val sessao = obterSessao()

    val parameters = call.receiveParameters()

    val password = (parameters["password"] ?: "")

    if (password.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Digite uma nova senha!")

    contasRepository.definirSenha(sessao.conta?.id!!, password)
    enviarEmailSenhaAlterada(emailSender, sessao.conta?.email!!)

    call.respondFragment(HttpStatusCode.OK) {
        botao(
            tipo = TiposBotaoEnum.NEUTRAL,
            hxPath = CaminhosBaseEnum.ALTERAR_SENHA_PERFIL,
            classes = "self-start",
            hxTarget = "form-perfil-alterar-senha"
        ) { +"Alterar senha" }
        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Senha alterada com sucesso!")
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
    contasRepository: ContasRepository,
    emailSender: EmailSender
) {
    val sessao = obterSessao()

    val parameters = call.receiveParameters()

    val nome = (parameters["nome"] ?: "").toString()
    val email = (parameters["email"] ?: "").toString()
    val usuario = parameters["usuario"]
    val password = parameters["password"] ?: gerarSenhaBasica()
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
        usuario = usuario ?: criarNomeDeUsuario(nome),
        senha = password,
        status = "ATIVO",
        tipoConta = perfilDeAcesso,
        imagemDePerfil = ""
    )
    contasRepository.criarUsuario(novaConta)

    enviarEmailNovaConta(emailSender, novaConta)

    call.respondFragment(HttpStatusCode.OK) {
        includeFormNovoUsuario()
        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Usuário cadastrado com sucesso")
    }
}