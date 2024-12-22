package com.pscarpellini.frontend.fragments.geral.botoes

import com.pscarpellini.frontend.enums.TiposBotaoEnum
import kotlinx.html.*

fun FlowContent.botao(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
    conteudo: FlowContent.() -> Unit
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "bg-high-dark text-low-light"} font-semibold ${if(small) "py-1" else "py-2"} px-6 ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        type = type
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        div(classes = "flex flex-row items-center justify-center") {
            conteudo(this)
        }
    }
}

