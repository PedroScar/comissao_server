package com.pscarpellini.frontend.fragments.geral.card

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.DirecoesEnum
import kotlinx.html.*

fun FlowContent.card(
    classes: String = "",
    arredondamento: ArredondamentosEnum = ArredondamentosEnum.LG,
    direcao: DirecoesEnum = DirecoesEnum.HORIZONTAL,
    showBordas: Boolean = false,
    showBackground: Boolean = true,
    usarPadding: Boolean = true,
    conteudo: FlowContent.() -> Unit
) {
    div(classes = " ${if (usarPadding) "p-4" else ""} ${if(showBackground) "bg-high-pure" else "bg-transparent"} ${if(showBordas) "border border-high-dark" else ""} ${direcao.cssProprio} ${arredondamento.cssProprio} $classes") {
        conteudo(this)
    }
}