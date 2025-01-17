package com.pscarpellini.frontend.fragments.geral.botoes

import com.pscarpellini.frontend.enums.designsystem.AlinhamentosEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.interfaces.IPaginaEnum
import kotlinx.html.*

fun FlowContent.botao(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
    alinhamento: AlinhamentosEnum = AlinhamentosEnum.CENTER,
    hxMethod: FormMethod = FormMethod.post,
    hxPath: IPaginaEnum? = null,
    hxTarget: String = "#",
    hxIndicator: String = "",
    hxSwap: String = "innerHTML",
    conteudo: FlowContent.() -> Unit
) {
    button(
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} font-semibold ${if(small) "py-1 px-4" else "py-2 px-6"} ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        type = type
    ) {
        attributes["hx-${hxMethod.name}"] = hxPath?.path ?: ""
        attributes["hx-target"] = "#$hxTarget"
        attributes["hx-swap"] = hxSwap
        if(hxIndicator.isNotEmpty()) attributes["hx-indicator"] = "#$hxIndicator"

        if (!enabled) attributes["disabled"] = "disabled"
        div(classes = "flex flex-row items-center $alinhamento") {
            conteudo(this)
        }
    }
}

fun FlowContent.botaoLink(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    link: String,
    alinhamento: AlinhamentosEnum = AlinhamentosEnum.CENTER,
    small: Boolean = false,
    enabled: Boolean = true,
    conteudo: FlowContent.() -> Unit
) {
    a(
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} font-semibold ${if(small) "py-1 px-4" else "py-2 px-6"} ${if(interativo) "cursor-pointer" else ""} flex flex-row items-center $alinhamento transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
        href = link
    ) {
        if (!enabled) attributes["disabled"] = "disabled"
        conteudo(this)
    }
}

fun FlowContent.botaoHX(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    link: IPaginaEnum,
    target: String = "conteudo-interno",
    alinhamento: AlinhamentosEnum = AlinhamentosEnum.CENTER,
    small: Boolean = false,
    enabled: Boolean = true,
    conteudo: FlowContent.() -> Unit
) {
    div (
        classes = "${if(enabled) tipo.cssProprio else "${tipo.cssDesabilitado} pointer-events-none"} font-semibold ${if(small) "py-1 px-4" else "py-2 px-6"} ${if(interativo) "cursor-pointer" else ""} flex flex-row items-center $alinhamento transition-all duration-300 ${if(!enabled) "text-low-light" else ""} $classes",
    ) {
        attributes["hx-post"] = link.path
        attributes["hx-trigger"] = "click"
        attributes["hx-target"] = "#$target"
        attributes["hx-replace-url"] = link.path
        attributes["hx-swap"] = "innerHTML"

        if (!enabled) attributes["disabled"] = "disabled"
        conteudo(this)
    }
}