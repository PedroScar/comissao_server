package com.pscarpellini.frontend.fragments.logados.menu_pilula

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.ItensMenuPilulaEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.a

fun FlowContent.itemMenuPilula(
    item: ItensMenuPilulaEnum,
) {
    a(href = item.caminho) {
        card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2 cursor-pointer hover:scale-105 hover:shadow-sm transition-all") {
            icone(item.icone, showBackground = true)
            +item.textoCTA
        }
    }
}