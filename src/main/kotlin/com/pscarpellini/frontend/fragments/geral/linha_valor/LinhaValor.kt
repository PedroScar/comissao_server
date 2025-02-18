package com.pscarpellini.frontend.fragments.geral.linha_valor

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import kotlinx.html.*

fun FlowContent.linhaValor(
    id: String? = null,
    hxSwapOob: String? = null,
    classes: String = "",
    titulo: String,
    valor: String?,
) {
    div(classes = classes) {
        if(id != null) attributes["id"] = id
        if(hxSwapOob != null) attributes["hx-swap-oob"] = hxSwapOob
        h5 { +titulo }
        span (classes = CoresEnum.LOW_LIGHT.text) { if(valor != null) +valor else +"-" }
    }
}
