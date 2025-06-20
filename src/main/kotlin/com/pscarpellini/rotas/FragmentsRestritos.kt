package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.repositories.interfaces.*
import com.pscarpellini.rotas.base.handleFragmentTabelaUsuarios
import com.pscarpellini.rotas.comissao.*
import io.ktor.http.*
import io.ktor.server.routing.*

fun Route.fragmentsRestritos(
    contasRepository: ContasRepository,
    saldosRepository: SaldosRepository,
    extratosRepository: ExtratosRepository,
    promocoesRepository: PromocoesRepository,
    videosRepository: VideosRepository
) {
    post(FragmentsRestritosEnum.FRAGMENT_MENU.path) {
        val sessao = obterSessao()
        call.respondFragment(HttpStatusCode.OK) { includeMenuPrincipal(sessao) }
    }

    post(FragmentsRestritosEnum.FRAGMENT_HEADER_INTERNO.path) {
        val sessao = obterSessao()
        call.respondFragment(HttpStatusCode.OK) { includeHeaderLogado(sessao = sessao) }
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_PROMOCOES.path) {
        handleFragmentTabelaPromocoes(promocoesRepository)
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_VIDEOS.path) {
        handleFragmentTabelaVideos(videosRepository)
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path) {
        handleFragmentTabelaUsuarios(contasRepository)
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES.path) {
        handleFragmentTabelaHistoricoDeTransacoes(extratosRepository)
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_SALDOS_DOS_PROMOTORES.path) {
        handleFragmentTabelaSaldosDosPromotores(saldosRepository)
    }

    post(FragmentsRestritosEnum.FRAGMENT_POPUP_MODIFICAR_SALDO.path) {
        handlePopupModificarSaldo()
    }

    post(FragmentsRestritosEnum.FRAGMENT_POPUP_MODIFICAR_SALDO_INFOS_PROMOTOR.path) {
        handlePopupModificarSaldoInfosPromotor(contasRepository)
    }
}

enum class FragmentsRestritosEnum(
    override val path: String,
) : IFragmentEnum {
    //    FRAGMENTS ISOLADOS
    FRAGMENT_MENU("/int/fragment/menu_principal"),
    FRAGMENT_HEADER_INTERNO("/int/fragment/header_interno"),

    FRAGMENT_TABELA_PROMOCOES("/int/fragment/promocoes"),
    FRAGMENT_TABELA_VIDEOS("/int/fragment/videos"),
    FRAGMENT_TABELA_USUARIOS("/int/fragment/tabela_usuarios"),
    FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES("/int/fragment/historico_de_transacoes"),
    FRAGMENT_TABELA_SALDOS_DOS_PROMOTORES("/int/fragment/saldos_dos_promotores"),

    FRAGMENT_POPUP_MODIFICAR_SALDO("/int/fragment/popup/modificar_saldo"),
    FRAGMENT_POPUP_MODIFICAR_SALDO_INFOS_PROMOTOR("/int/fragment/popup/modificar_saldo/infos/promotor"),
}