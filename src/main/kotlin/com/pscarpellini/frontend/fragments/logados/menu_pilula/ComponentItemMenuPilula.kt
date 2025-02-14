package com.pscarpellini.frontend.fragments.logados.menu_pilula

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.ItensMenuPilulaEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.css.div
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div

fun FlowContent.itemMenuPilula(
    item: ItensMenuPilulaEnum,
) {
    div {
        attributes["hx-post"] = item.pagina.path
        attributes["hx-trigger"] = "click"
        if(item.usarReplace) attributes["hx-replace-url"] = item.pagina.path
        attributes["hx-swap"] = "innerHTML"
        card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2 cursor-pointer hover:scale-105 hover:shadow-sm transition-all") {
            icone(item.icone, showBackground = true)
            +item.textoCTA
        }
    }
}