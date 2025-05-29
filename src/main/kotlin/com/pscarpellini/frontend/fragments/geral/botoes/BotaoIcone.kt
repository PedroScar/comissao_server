package com.pscarpellini.frontend.fragments.geral.botoes

import com.pscarpellini.frontend.enums.designsystem.AlinhamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun FlowContent.botaoIcone(
    icone: IconesEnum,
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    id: String? = null,
    type: ButtonType = ButtonType.submit,
) {
    button(
        classes = "${tipo.cssProprio} disabled:pointer-events-none font-semibold ${if(small) "p-1" else "p-2"} ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) CoresEnum.LOW_LIGHT.text else ""} $classes",
        type = type
    ) {
        if (id != null) attributes["id"] = id
        if (!enabled) attributes["disabled"] = "disabled"
        icone(icone = icone, usarPadding = false, classes = if(enabled) "" else "opacity-30")
    }
}

fun FlowContent.botaoIcone(
    id: String? = null,
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    enabled: Boolean = true,
    type: ButtonType = ButtonType.submit,
    alinhamento: AlinhamentosEnum = AlinhamentosEnum.CENTER,
    hxMethod: FormMethod = FormMethod.post,
    hxPath: String,
    hxParams: Map<String, String> = mapOf(),
    hxTarget: String = "",
    hxIndicator: String = "",
    hxReplaceUrl: String = "",
    hxSwap: String = "innerHTML",
    hxEncoding: String = "",
    isAutovalidateButton: Boolean = true,
    icone: IconesEnum,
) {
    button(
        classes = "${tipo.cssProprio} disabled:pointer-events-none font-semibold ${if(small) "p-1" else "p-2"} ${if(interativo) "cursor-pointer" else ""} transition-all duration-300 ${if(!enabled) CoresEnum.LOW_LIGHT.text else ""} $classes",
        type = type
    ) {
        if(id != null) attributes["id"] = id
        if(isAutovalidateButton) attributes["ktAutovalidateButton"] = ""
        attributes["hx-${hxMethod.name}"] = hxPath
        if(hxTarget.isNotEmpty()) {
            attributes["hx-target"] = "#$hxTarget"
            attributes["hx-swap"] = hxSwap
        } else attributes["hx-swap"] = "none"
        if(hxIndicator.isNotEmpty()) attributes["hx-indicator"] = "#$hxIndicator"
        if(hxEncoding.isNotEmpty()) attributes["hx-encoding"] = hxEncoding
        attributes["hx-vals"] = Json.encodeToString(hxParams)
        if(hxReplaceUrl.isNotEmpty()) attributes["hx-replace-url"] = hxReplaceUrl

        if (!enabled) attributes["disabled"] = "disabled"
        icone(icone = icone, usarPadding = false, classes = if(enabled) "" else "opacity-30")
    }
}