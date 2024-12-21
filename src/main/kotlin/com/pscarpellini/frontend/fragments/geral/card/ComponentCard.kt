package com.pscarpellini.frontend.fragments.geral.card

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import kotlinx.html.*

fun FlowContent.card(
    classes: String = "",
    arredondamento: ArredondamentosEnum = ArredondamentosEnum.LG,
    showBordas: Boolean = false,
    showBackground: Boolean = true,
    conteudo: FlowContent.() -> Unit
) {
    div(classes = "p-6 ${if(showBackground) "bg-high-pure" else "bg-transparent"} ${if(showBordas) "border border-high-dark" else ""} ${arredondamento.cssProprio} $classes") {
        conteudo(this)
    }
}