package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.conta.miniConta
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.SaldoVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.span

private val HEADERS = arrayListOf("Nome do promotor", "Saldo atual", "Total de vendas", "")


fun FlowContent.includeTabelaDeSaldos(
    saldos: List<SaldoVO>?,
    listaTransacoes: List<Triple<Int, Int, Int>>?
) {
    val listaSaldos: List<SaldoVO> = saldos ?: arrayListOf()

    val doubleSaldoTransacao = listaSaldos
        .map { saldo ->
            val transacoes = listaTransacoes?.first { it.first == saldo.conta.id }
            saldo to (transacoes?.second ?: 0) + (transacoes?.third ?: 0)
        }
        .sortedByDescending { it.second }

    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = doubleSaldoTransacao.map { (saldo, transacao) -> exibirLinhaSaldo(saldo, transacao) },
        classes = "h-full w-full"
    )
}


private fun exibirLinhaSaldo(saldo: SaldoVO, transacoes: Int): List<FlowContent.() -> Unit> {
    return listOf(
        {
            miniConta(conta = saldo.conta, exibirPerfil = false)
        },
        {
            div(classes = "flex flex-row w-64 justify-between gap-2 ${CoresEnum.BRAND_LIGHT.bg} ${CoresEnum.LOW_DARK.text} items-center font-semibold ${ArredondamentosEnum.PILL} py-2 px-4") {
                span(classes = "grow") { +saldo.saldo.formatarValorMonetario() }
                botao(tipo = TiposBotaoEnum.PRIMARY) { +"Modificar" }
            }
        },
        { +"$transacoes" },
        {
            botaoIcone(icone = IconesEnum.MAIS, tipo = TiposBotaoEnum.NEUTRAL)
        }
    )
}