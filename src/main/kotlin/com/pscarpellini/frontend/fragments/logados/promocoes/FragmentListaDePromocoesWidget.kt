package com.pscarpellini.frontend.fragments.logados.promocoes

import com.pscarpellini.extensions.formatarIntervaloDeDatas
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent

fun FlowContent.includeListaDePromocoesWidget(
    promocoes: List<PromocaoVO>?
) {
    val promocoesTabela: List<PromocaoVO> = promocoes ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = arrayListOf("Nome da promoção", "Data de início e fim", "Vendas"),
        linhas = promocoesTabela.map { promocao ->
            exibirPromocao(promocao)
        },
        classes = "h-full w-full"
    )
}

private fun exibirPromocao(promocao: PromocaoVO): List<FlowContent.() -> Unit> {
    return listOf(
        { +promocao.titulo },
        { +formatarIntervaloDeDatas(promocao.dataDisponivel, promocao.dataValidade) },
        { +promocao.subtitulo },
    )
}