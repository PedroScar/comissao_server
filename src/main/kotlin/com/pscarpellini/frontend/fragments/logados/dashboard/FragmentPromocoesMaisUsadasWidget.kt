package com.pscarpellini.frontend.fragments.logados.dashboard

import com.pscarpellini.extensions.formatarIntervaloDeDatas
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent

private val HEADERS = arrayListOf("Nome da promoção", "Data de início e fim", "Vendas", "Compras")

fun FlowContent.includePromocoesMaisUtilizadasWidget(
    promocoes: List<PromocaoVO>?
) {
    val promocoesTabela: List<PromocaoVO> = promocoes ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = promocoesTabela.map { promocao ->
            exibirPromocao(promocao)
        },
        classes = "h-full w-full"
    )
}

private fun exibirPromocao(promocao: PromocaoVO): List<FlowContent.() -> Unit> = listOf(
    { +promocao.titulo },
    { +formatarIntervaloDeDatas(promocao.dataDisponivel, promocao.dataValidade) },
    { +"${promocao.vendas}" },
    { +"${promocao.compras}" }
)