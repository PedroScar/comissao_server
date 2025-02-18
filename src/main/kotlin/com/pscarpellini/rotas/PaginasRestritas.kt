package com.pscarpellini.rotas

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.*
import com.pscarpellini.frontend.pages.restritos.base.*
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.rotas.base.*
import com.pscarpellini.rotas.comissao.*
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasRestritas(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    extratosRepository: ExtratosRepository
) {
    get(PaginasRestritasEnum.INTERNO.caminho.path) { handleInterno() }
    post(PaginasRestritasEnum.INICIO.caminho.path) { handleInicio() }
    get(PaginasRestritasEnum.LOGOUT.caminho.path) { handleLogout() }
    post(PaginasRestritasEnum.CONFIGURACOES_DO_APP.caminho.path) { handleConfiguracoesDoApp() }

    post(PaginasComissaoEnum.PROMOCOES.caminho.path) { handlePromocoes() }
    post(PaginasComissaoEnum.NOVA_PROMOCAO.caminho.path) { handleNovaPromocao() }
    post(PaginasComissaoEnum.EXIBIR_PROMOCAO.caminho.path) { handleExibirPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_NOVA_PROMOCAO.path) { handleFormularioNovaPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_ENCERRAR_PROMOCAO.path) { handleEncerrarPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.SELECT_PROMOCOES_ATIVAS.path) { handleSelectPromocoesAtivas(promocoesRepository) }

    post(PaginasComissaoEnum.SALDOS_DOS_PROMOTORES.caminho.path) { handleSaldosDosPromotores() }
    post(CaminhosComissaoEnum.SELECT_PROMOTORES.path) { handleSelectPromotores(contasRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_ALTERAR_SALDO.path) { handleAlterarSaldo(extratosRepository) }

    post(PaginasComissaoEnum.RELATORIOS.caminho.path) { handleRelatorios() }

    post(PaginasComissaoEnum.HISTORICO_DE_TRANSACOES.caminho.path) { handleHistoricoDeTransacoes() }

    post(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.caminho.path) { handleGerenciamentoDeUsuarios() }
    post(PaginasRestritasEnum.MEU_PERFIL.caminho.path) { handleMeuPerfil() }
    post(PaginasRestritasEnum.NOVO_USUARIO.caminho.path) { handleNovoUsuario() }
    post(PaginasRestritasEnum.FORMULARIO_NOVO_USUARIO.caminho.path) { handleFormularioNovoUsuario(contasRepository) }
}