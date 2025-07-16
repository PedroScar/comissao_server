package com.pscarpellini.rotas

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.repositories.interfaces.VideosRepository
import com.pscarpellini.rotas.base.*
import com.pscarpellini.rotas.comissao.*
import com.pscarpellini.tools.email.EmailSender
import io.ktor.server.routing.*

fun Route.paginasRestritas(
    contasRepository: ContasRepository,
    clienteRepository: ClienteRepository,
    promocoesRepository: PromocoesRepository,
    extratosRepository: ExtratosRepository,
    videosRepository: VideosRepository,
    emailSender: EmailSender
) {
    get(PaginasRestritasEnum.INTERNO.caminho.path) { handleInterno() }
    post(PaginasRestritasEnum.INICIO.caminho.path) { handleInicio() }
    get(PaginasRestritasEnum.LOGOUT.caminho.path) { handleLogout() }

    post(PaginasRestritasEnum.EXIBIR_CONFIGURACOES_DO_APP.caminho.path) { handleExibirConfiguracoesDoApp(clienteRepository) }
    post(PaginasRestritasEnum.EDITAR_CONFIGURACOES_DO_APP.caminho.path) { handleEditarConfiguracoesDoApp(clienteRepository) }
    post(CaminhosBaseEnum.FORMULARIO_EDITAR_CONFIGURACOES_APP.path) { handleFormularioEditarConfiguracoesApp(clienteRepository) }

    post(PaginasComissaoEnum.PROMOCOES.caminho.path) { handlePromocoes() }
    post(PaginasComissaoEnum.NOVA_PROMOCAO.caminho.path) { handleNovaPromocao() }
    post(PaginasComissaoEnum.EXIBIR_PROMOCAO.caminho.path) { handleExibirPromocao(promocoesRepository) }
    post(PaginasComissaoEnum.EDITAR_PROMOCAO.caminho.path) { handleEditarPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.SELECT_PROMOCOES_ATIVAS.path) { handleSelectPromocoesAtivas(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_CRIAR_PROMOCAO.path) { handleFormularioCriarPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_ENCERRAR_PROMOCAO.path) { handleEncerrarPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_REMOVER_PROMOCAO.path) { handleRemoverPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_EDITAR_PROMOCAO.path) { handleFormularioEditarPromocao(promocoesRepository) }

    post(PaginasComissaoEnum.VIDEOS.caminho.path) { handleVideos() }
    post(PaginasComissaoEnum.CRIAR_VIDEO.caminho.path) { handleCriarVideo() }
    post(PaginasComissaoEnum.EXIBIR_VIDEO.caminho.path) { handleExibirVideo(videosRepository) }
    post(PaginasComissaoEnum.EDITAR_VIDEO.caminho.path) { handleEditarVideo(videosRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_CRIAR_VIDEO.path) { handleFormularioNovoVideo(videosRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_HABILITAR_VIDEO.path) { handleHabilitarDesabilitarVideo(videosRepository, habilitar = true) }
    post(CaminhosComissaoEnum.FORMULARIO_DESABILITAR_VIDEO.path) { handleHabilitarDesabilitarVideo(videosRepository, habilitar = false) }
    post(CaminhosComissaoEnum.FORMULARIO_REMOVER_VIDEO.path) { handleRemoverVideo(videosRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_EDITAR_VIDEO.path) { handleFormularioEditarVideo(videosRepository) }

    post(PaginasComissaoEnum.SALDOS_DOS_PROMOTORES.caminho.path) { handleSaldosDosPromotores() }
    post(CaminhosComissaoEnum.SELECT_PROMOTORES.path) { handleSelectPromotores(contasRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_ALTERAR_SALDO.path) { handleAlterarSaldo(extratosRepository) }

    post(PaginasComissaoEnum.RELATORIOS.caminho.path) { handleRelatorios() }

    post(PaginasComissaoEnum.HISTORICO_DE_TRANSACOES.caminho.path) { handleHistoricoDeTransacoes() }

    post(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.caminho.path) { handleGerenciamentoDeUsuarios() }
    post(PaginasRestritasEnum.NOVO_USUARIO.caminho.path) { handleNovoUsuario() }
    post(PaginasRestritasEnum.FORMULARIO_NOVO_USUARIO.caminho.path) { handleFormularioNovoUsuario(contasRepository, emailSender) }

    post(PaginasRestritasEnum.MEU_PERFIL.caminho.path) { handleMeuPerfil() }
    post(PaginasRestritasEnum.EDITAR_MEU_PERFIL.caminho.path) { handleEditarMeuPerfil() }
    post(CaminhosBaseEnum.FORMULARIO_EDITAR_MEU_PERFIL.path) { handleFormularioEditarMeuPerfil(contasRepository) }
    post(CaminhosBaseEnum.ALTERAR_SENHA_PERFIL.path) { handleMeuPerfilAlterarSenha() }
    post(CaminhosBaseEnum.FORMULARIO_ALTERAR_SENHA_PERFIL.path) { handleFormularioMeuPerfilAlterarSenha(contasRepository, emailSender) }

}