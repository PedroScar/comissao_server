package com.pscarpellini.frontend.fragments.geral.avatar

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

fun FlowContent.avatar(
    nome: String,
    imagemUrl: String? = null,
    base64: String? = null,
    tipo: TiposAvatarEnum = TiposAvatarEnum.MEDIUM_CIRCLE,
    classes: String = "",
) {
    div(
        classes = "inline-flex aspect-square items-center justify-center ${CoresEnum.BRAND_LIGHT.bg} ${CoresEnum.BRAND_DARK.text} font-semibold $tipo $classes"
    ) {
        if (!imagemUrl.isNullOrBlank()) {
            img(
                src = "data:image/png;base64, $imagemUrl",
//                src = "/public/$imagemUrl",
                alt = nome,
                classes = "object-cover $tipo"
            )
        } else if(!base64.isNullOrBlank()) {
            img(
                src = "data:image/png;base64, $base64",
                alt = nome,
                classes = "object-cover $tipo"
            )
        } else +nome.firstOrNull()?.toString()?.uppercase().orEmpty()
    }
}
