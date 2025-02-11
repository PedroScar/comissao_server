package com.pscarpellini.rotas.comissao

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.dashboard.includeTransacoesRecentesWidget
import com.pscarpellini.frontend.fragments.logados.dashboard.includePromocoesMaisUtilizadasWidget
import com.pscarpellini.frontend.fragments.logados.dashboard.includeVisaoGeralWidget
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContadoresDashboardViewRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.server.routing.*


suspend fun RoutingContext.handleWidgetVisaoGeral(contadoresDashboardViewRepository: ContadoresDashboardViewRepository) {
    val sessao = obterSessao()
    contadoresDashboardViewRepository.carregarVisaoGeral(sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao contar as promoções ativas")
            is DbResponse.Successo -> { call.respondFragment { includeVisaoGeralWidget(visaoGeral = resposta.data) } }
        }
    }
}

suspend fun RoutingContext.handleWidgetPromocoesMaisUtilizadas(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    promocoesRepository.carregarPromocoesMaisUtilizadas(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao carregar promoções")
            is DbResponse.Successo -> { call.respondFragment { includePromocoesMaisUtilizadasWidget(promocoes = resposta.data) } }
        }
    }
}

suspend fun RoutingContext.handleWidgetTransacoesRecentes(extratosRepository: ExtratosRepository) {
    val sessao = obterSessao()
    extratosRepository.carregarExtratosRecentes(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao carregar as últimas transações")
            is DbResponse.Successo -> { call.respondFragment { includeTransacoesRecentesWidget(transacoes = resposta.data) } }
        }
    }
}