package com.pscarpellini.frontend.fragments.geral.avatar

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import kotlinx.html.FlowContent
import kotlinx.html.ImgLoading
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.onError

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
                loading = ImgLoading.lazy,
                src = "data:image/png;base64, $imagemUrl",
                alt = nome.firstOrNull()?.toString()?.uppercase().orEmpty(),
                classes = "object-cover $tipo text-center items-center",
            ) {
                onError = "handleImageError(this, '${nome.firstOrNull()?.toString()?.uppercase().orEmpty()}')"
            }
        } else if(!base64.isNullOrBlank()) {
            img(
                loading = ImgLoading.lazy,
                src = "data:image/png;base64, $base64",
                alt = nome.firstOrNull()?.toString()?.uppercase().orEmpty(),
                classes = "object-cover $tipo text-center items-center",
            ) {
                onError = "handleImageError(this, '${nome.firstOrNull()?.toString()?.uppercase().orEmpty()}')"
            }
        } else +nome.firstOrNull()?.toString()?.uppercase().orEmpty()
    }
}
