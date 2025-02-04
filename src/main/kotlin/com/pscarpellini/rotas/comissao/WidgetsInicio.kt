package com.pscarpellini.rotas.comissao

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.historico_de_transacoes.includeTabelaDeHistoricoDeTransacoes
import com.pscarpellini.frontend.fragments.logados.historico_de_transacoes.includeWidgetHistoricoDeTransacoes
import com.pscarpellini.frontend.fragments.logados.promocoes.includeListaDePromocoesWidget
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*


suspend fun RoutingContext.handleWidgetContagens(promocoesRepository: PromocoesRepository, extratosRepository: ExtratosRepository, contasRepository: ContasRepository) {
    val sessao = obterSessao()
    promocoesRepository.contarPromocoesAtivas(sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao contar as promoções ativas")
            is DbResponse.Successo -> { call.respondFragment { +"Contagem de promoções: ${resposta.data}" } }
        }
    }
}

suspend fun RoutingContext.handleWidgetPromocoes(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    promocoesRepository.carregarPromocoesMaisUtilizadas(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao carregar promoções")
            is DbResponse.Successo -> { call.respondFragment { includeListaDePromocoesWidget(promocoes = resposta.data) } }
        }
    }
}

suspend fun RoutingContext.handleWidgetHistoricoDeTransacoes(extratosRepository: ExtratosRepository) {
    val sessao = obterSessao()
    extratosRepository.carregarExtratosRecentes(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao carregar as últimas transações")
            is DbResponse.Successo -> { call.respondFragment { includeWidgetHistoricoDeTransacoes(transacoes = resposta.data) } }
        }
    }
}