package com.pscarpellini.frontend.fragments.logados.dashboard

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.VisaoGeralVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeVisaoGeralWidget(
    visaoGeral: VisaoGeralVO?
) {
    div(classes = "grid grid-rows-3 gap-4 w-full h-full") {
        card(showBordas = true, classes = "flex flex-row justify-between gap-4") {
            span(classes = "${CoresEnum.LOW_PURE.text}") { +"Promoções ativas" }
            div(classes = "text-2xl font-semibold") {
                +"${visaoGeral?.quantidadePromocoesAtivas ?: 0}"
            }
        }
        card(showBordas = true, classes = "flex flex-row justify-between gap-4") {
            span(classes = "${CoresEnum.LOW_PURE.text}") { +"Promotores cadastrados" }
            div(classes = "text-2xl font-semibold") {
                +"${visaoGeral?.quantidadePromotores ?: 0}"
            }
        }
        card(showBordas = true, classes = "flex flex-row justify-between gap-4") {
            span(classes = "${CoresEnum.LOW_PURE.text}") { +"Comissões do mês" }
            div(classes = "text-2xl font-semibold") {
                +"R$ ${visaoGeral?.valorComissoesMesAtual ?: 0.00}"
            }
        }
    }
}