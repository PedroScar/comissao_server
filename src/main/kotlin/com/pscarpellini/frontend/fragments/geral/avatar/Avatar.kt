package com.pscarpellini.frontend.fragments.geral.avatar

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

fun FlowContent.avatar(
    nome: String,
    imagemUrl: String? = null,
    tipo: TiposAvatarEnum = TiposAvatarEnum.MEDIUM_CIRCLE,
    classes: String = "",
) {
    div(
        classes = "inline-flex aspect-square items-center justify-center ${CoresEnum.BRAND_LIGHT.bg} ${CoresEnum.BRAND_DARK.text} font-semibold $tipo $classes"
    ) {
        if (!imagemUrl.isNullOrBlank()) {
            img(
                src = imagemUrl,
                alt = nome,
                classes = "object-cover"
            )
        } else +nome.firstOrNull()?.toString()?.uppercase().orEmpty()
    }
}
