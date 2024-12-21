package com.pscarpellini.frontend.fragments.geral.button

import kotlinx.html.*

fun FlowContent.botao(
    tipo: TipoBotaoEnum = TipoBotaoEnum.PRIMARY,
    classes: String = "",
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
    conteudo: FlowContent.() -> Unit
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "bg-high-dark text-low-light"} font-semibold rounded-pill ${if(small) "py-1" else "py-2"} px-6 cursor-pointer text-low-pure transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        type = type
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        div(classes = "flex flex-row items-center justify-center") {
            conteudo(this)
        }
    }
}

enum class TipoBotaoEnum(
    val cssProprio: String,
) {
    PRIMARY(
        cssProprio = "bg-brand-pure hover:bg-brand-medium"
    ),
    NEUTRAL(
        cssProprio = "bg-high-light hover:bg-high-medium"
    ),
    SUBTLE(
        cssProprio = "bg-none border border-transparent hover:border hover:border-high-dark"
    )
}