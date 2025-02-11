package com.pscarpellini.frontend.fragments.logados.dashboard

import com.pscarpellini.extensions.formatarDataHora
import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.fragments.geral.conta.miniConta
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ExtratoVO
import kotlinx.html.FlowContent
import kotlinx.html.span

private val HEADERS = arrayListOf("Data e hora", "Responsável", "Tipo de transação", "Valor")

fun FlowContent.includeTransacoesRecentesWidget(
    transacoes: List<ExtratoVO>?
) {
    val extrato: List<ExtratoVO> = transacoes ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = extrato.map { transacao ->
            exibirLinhaTransacao(transacao)
        },
        classes = "h-full w-full"
    )
}

private fun exibirLinhaTransacao(transacao: ExtratoVO): List<FlowContent.() -> Unit> {
    return listOf(
        { +transacao.dataCriacao.formatarDataHora() },
        { miniConta(transacao.contaResponsavel) },
        { if(transacao.isCredito) +"Saldo adicionado" else +"Saldo retirado" },
        {
            span(classes = "font-semibold ${if(transacao.isCredito) CoresEnum.SUCCESS_DARK.text else CoresEnum.ALERT_DARK.text}") {
                if(transacao.isCredito) +"+" else +"-"
                +transacao.valor.formatarValorMonetario()
            }
        },
    )
}