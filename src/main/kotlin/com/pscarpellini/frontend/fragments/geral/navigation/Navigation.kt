package com.pscarpellini.frontend.fragments.geral.navigation

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.interfaces.IPaginaEnum
import kotlinx.html.*

fun FlowContent.navigationHX(
    classes: String = "",
    hxMethod: FormMethod = FormMethod.post,
    hxPath: IPaginaEnum,
    hxTarget: String = "#",
    hxIndicator: String = "",
    hxSwap: String = "innerHTML",
    texto: String,
    icone: IconesEnum? = null
) {
    a(href = "", classes = "hover:underline") {
        attributes["hx-${hxMethod.name}"] = hxPath.path
        attributes["hx-target"] = "#$hxTarget"
        attributes["hx-swap"] = hxSwap
        if(hxIndicator.isNotEmpty()) attributes["hx-indicator"] = "#$hxIndicator"
        card(showBackground = false, usarPadding = false, classes = classes) {
            icone?.let { icone(it, size = 2f) }
            +texto
        }
    }
}

fun FlowContent.navigationLink(
    classes: String = "",
    href: String = "",
    texto: String,
    icone: IconesEnum? = null
) {
    a(href = href, classes = "hover:underline") {
        card(showBackground = false, usarPadding = false, classes = classes) {
            icone?.let { icone(it, size = 2f) }
            +texto
        }
    }
}