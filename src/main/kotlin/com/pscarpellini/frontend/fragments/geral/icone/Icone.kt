package com.pscarpellini.frontend.fragments.geral.icone

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import kotlinx.html.FlowContent
import kotlinx.html.img

fun FlowContent.icone(
    icone: IconesEnum,
    showBackground: Boolean = false,
    usarPreenchido: Boolean = false,
    corFundo: CoresEnum = CoresEnum.BRAND_PURE,
    size: Float? = null,
    classes: String = "",
    usarPadding: Boolean = true,
    id: String = "icone-${System.currentTimeMillis()}",
) {
    img(
        classes = "aspect-square ${if (usarPadding) "p-2" else ""} ${if (size != null) "size-[${size}rem]" else ""} ${if (showBackground) "bg-${corFundo.cssProprio} rounded-pill" else "bg-transparent"} $classes",
        src = if (usarPreenchido) icone.caminhoDoIconePreenchido ?: icone.caminhoDoIcone else icone.caminhoDoIcone
    ) {
        attributes["id"] = id
    }
}