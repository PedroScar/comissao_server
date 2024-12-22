package com.pscarpellini.frontend.fragments.geral.botoes

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.botaoIcone(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
    icone: IconesEnum
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "bg-high-dark text-low-light"} font-semibold ${if(small) "p-1" else "p-2"} ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        type = type
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        icone(icone = icone, usarPadding = false)
    }
}