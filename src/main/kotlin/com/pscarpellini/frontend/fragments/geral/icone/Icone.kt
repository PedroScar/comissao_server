package com.pscarpellini.frontend.fragments.geral.icone

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import kotlinx.html.FlowContent
import kotlinx.html.img

fun FlowContent.icone(
    icone: IconesEnum,
    showBackground: Boolean = false,
    usarPreenchido: Boolean = false,
    corFundo: CoresEnum = CoresEnum.BRAND_PURE,
    size: Int? = null,
    classes: String = "",
    usarPadding: Boolean = true
) {
    img(
        classes = "aspect-square ${if (usarPadding) "p-2" else ""} ${if(size != null) "size-[${size}rem]" else ""} ${if(showBackground) "bg-${corFundo.cssProprio} rounded-pill" else "bg-transparent"} $classes",
        src = if(usarPreenchido) icone.caminhoDoIconePreenchido ?: icone.caminhoDoIcone else icone.caminhoDoIcone
    )
}