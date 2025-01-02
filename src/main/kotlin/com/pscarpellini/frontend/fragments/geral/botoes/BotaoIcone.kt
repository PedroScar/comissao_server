package com.pscarpellini.frontend.fragments.geral.botoes

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.botaoIcone(
    icone: IconesEnum,
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} font-semibold ${if(small) "p-1" else "p-2"} ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) CoresEnum.LOW_LIGHT.text else ""} $classes",
        type = type
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        icone(icone = icone, usarPadding = false, classes = if(enabled) "" else "opacity-30")
    }
}