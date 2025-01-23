package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeTabelaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.promocoes.includeTabelaDePromocoes
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.rotas.base.handleFragmentTabelaUsuarios
import com.pscarpellini.rotas.comissao.handleFragmentTabelaHistoricoDeTransacoes
import com.pscarpellini.rotas.comissao.handleFragmentTabelaPromocoes
import com.pscarpellini.rotas.comissao.handleFragmentTabelaSaldosDosPromotores
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.fragmentsRestritos(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
) {
    post(FragmentsRestritosEnum.FRAGMENT_MENU.path) {
        val sessao = obterSessao()
        call.respondFragment (HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
        }
    }

    post(FragmentsRestritosEnum.FRAGMENT_HEADER_INTERNO.path) {
        val sessao = obterSessao()
        call.respondFragment (HttpStatusCode.OK) {
            includeHeaderLogado(sessao = sessao)
        }
    }


    post(FragmentsRestritosEnum.FRAGMENT_TABELA_PROMOCOES.path) { handleFragmentTabelaPromocoes(promocoesRepository) }
    post(FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path) { handleFragmentTabelaUsuarios(contasRepository) }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES.path) { handleFragmentTabelaHistoricoDeTransacoes(contasRepository) }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_SALDOS_DOS_PROMOTORES.path) { handleFragmentTabelaSaldosDosPromotores(contasRepository) }
}

enum class FragmentsRestritosEnum(
    override val path: String,
): IFragmentEnum {
    //    FRAGMENTS ISOLADOS
    FRAGMENT_MENU("/int/fragment/menu_principal"),
    FRAGMENT_HEADER_INTERNO("/int/fragment/header_interno"),

    FRAGMENT_TABELA_PROMOCOES("/int/fragment/promocoes"),
    FRAGMENT_TABELA_USUARIOS("/int/fragment/tabela_usuarios"),
    FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES("/int/fragment/historico_de_transacoes"),
    FRAGMENT_TABELA_SALDOS_DOS_PROMOTORES("/int/fragment/saldos_dos_promotores"),
}