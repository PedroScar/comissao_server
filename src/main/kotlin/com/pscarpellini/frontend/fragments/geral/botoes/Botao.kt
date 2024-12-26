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
    hxMethod: FormMethod = FormMethod.post,
    hxPath: String = "",
    hxTarget: String = "#",
    hxSwap: String = "innerHTML",
    conteudo: FlowContent.() -> Unit
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} font-semibold ${if(small) "py-1" else "py-2"} px-6 ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        type = type
    ) {
        attributes["hx-${hxMethod.name}"] = hxPath
        attributes["hx-target"] = "#$hxTarget"
        attributes["hx-swap"] = hxSwap

        if (!enabled) attributes["disabled"] = "disabled"
        div(classes = "flex flex-row items-center justify-center") {
            conteudo(this)
        }
    }
}

fun FlowContent.botaoLink(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    link: String,
    small: Boolean = false,
    enabled: Boolean = true,
    conteudo: FlowContent.() -> Unit
) {
    a(
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} text-center font-semibold ${if(small) "py-1" else "py-2"} px-6 ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        href = link
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        conteudo(this)
    }
}