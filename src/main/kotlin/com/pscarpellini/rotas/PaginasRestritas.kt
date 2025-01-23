package com.pscarpellini.rotas

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.comissao.PaginasComissaoEnum
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
import com.pscarpellini.frontend.pages.restritos.comissao.visualizarPromocao
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.repositories.interfaces.ContasRepository
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
) {
    get(PaginasRestritasEnum.INTERNO.caminho.path) { handleInterno() }
    post(PaginasRestritasEnum.INICIO.caminho.path) { handleInicio() }
    post(PaginasRestritasEnum.LOGOUT.caminho.path) { handleLogout() }
    post(PaginasRestritasEnum.CONFIGURACOES_DO_APP.caminho.path) { handleConfiguracoesDoApp() }

    post(PaginasComissaoEnum.PROMOCOES.caminho.path) { handlePromocoes() }
    post(PaginasComissaoEnum.NOVA_PROMOCAO.caminho.path) { handleNovaPromocao() }
    post(PaginasComissaoEnum.EXIBIR_PROMOCAO.caminho.path) { handleExibirPromocao(promocoesRepository) }
    post(CaminhosComissaoEnum.FORMULARIO_NOVA_PROMOCAO.path) { handleFormularioNovaPromocao(promocoesRepository) }

    post(PaginasComissaoEnum.SALDOS_DOS_PROMOTORES.caminho.path) { handleSaldosDosPromotores() }

    post(PaginasComissaoEnum.RELATORIOS.caminho.path) { handleRelatorios() }

    post(PaginasComissaoEnum.HISTORICO_DE_TRANSACOES.caminho.path) { handleHistoricoDeTransacoes() }

    post(PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.caminho.path) { handleGerenciamentoDeUsuarios() }
    post(PaginasRestritasEnum.MEU_PERFIL.caminho.path) { handleMeuPerfil() }
    post(PaginasRestritasEnum.NOVO_USUARIO.caminho.path) { handleNovoUsuario() }
    post(PaginasRestritasEnum.FORMULARIO_NOVO_USUARIO.caminho.path) { handleFormularioNovoUsuario(contasRepository) }
}