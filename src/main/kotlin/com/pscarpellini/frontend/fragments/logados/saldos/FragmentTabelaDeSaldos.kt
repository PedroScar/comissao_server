package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.conta.miniConta
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.frontend.style.Colors
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SaldoVO
import io.ktor.server.util.*
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.span

private val HEADERS = arrayListOf("Nome do promotor", "Saldo atual", "Total de vendas", "")


fun FlowContent.includeTabelaDeSaldos(
    saldos: List<SaldoVO>?
) {
    val listaSaldos: List<SaldoVO> = saldos ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = listaSaldos.map { saldo ->
            exibirLinhaSaldo(saldo)
        },
        classes = "h-full w-full"
    )
}



private fun exibirLinhaSaldo(saldo: SaldoVO): List<FlowContent.() -> Unit> {
    return listOf(
        {
            miniConta(conta = saldo.conta, exibirPerfil = false)
        },
        {
            div(classes = "flex flex-row w-64 justify-between gap-2 ${CoresEnum.BRAND_LIGHT.bg} ${CoresEnum.LOW_DARK.text} items-center font-semibold ${ArredondamentosEnum.PILL} py-2 px-4") {
                span(classes = "grow") { +saldo.saldo.formatarValorMonetario() }
            }
        },
        { +"5" },
        {
            botaoIcone(icone = IconesEnum.MAIS, tipo = TiposBotaoEnum.NEUTRAL)
//            div(classes = "flex flex-row gap-2") {
//                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Editar"))
//                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Visualizar"))
//            }
        }
    )
}