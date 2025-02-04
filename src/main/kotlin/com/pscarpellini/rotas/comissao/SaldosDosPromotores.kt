package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeTabelaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.saldos.includeTabelaDeSaldos
import com.pscarpellini.frontend.pages.restritos.comissao.saldosDosPromotores
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.SaldosRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleFragmentTabelaSaldosDosPromotores(saldosRepository: SaldosRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""

    val sessao = obterSessao()
    saldosRepository.carregarSaldoContas(nome = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
            is DbResponse.Successo -> { call.respondFragment { includeTabelaDeSaldos(saldos = resposta.data) } }
        }
    }
}

suspend fun RoutingContext.handleSaldosDosPromotores() {
    val sessao = obterSessao()
    sessao.menuSelecionado = ItensMenuEnum.SALDOS_DOS_PROMOTORES
    sessao.paginaAtual = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        saldosDosPromotores(sessao = sessao)
    }
}