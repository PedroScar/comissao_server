package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.comissao.historicoDeTransacoes
import com.pscarpellini.frontend.pages.restritos.comissao.saldosDosPromotores
import io.ktor.http.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleHistoricoDeTransacoes() {
    val sessao = obterSessao()
    sessao.menuSelecionado = ItensMenuEnum.HISTORICO_DE_TRANSACOES
    sessao.paginaAtual = PaginasComissaoEnum.HISTORICO_DE_TRANSACOES
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        historicoDeTransacoes()
    }
}