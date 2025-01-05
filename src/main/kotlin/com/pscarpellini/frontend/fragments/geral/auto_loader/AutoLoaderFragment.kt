package com.pscarpellini.frontend.fragments.geral.auto_loader

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.loading.loading
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.autoLoaderFragment(
    id: String,
    path: String,
    isVerticalLoading: Boolean = false,
    classes: String = "",
) {
    val idDoConteudo = "$id-${System.currentTimeMillis()}"
    div(classes = classes) {
        attributes["hx-post"] = path
        attributes["hx-trigger"] = "load"
        attributes["hx-target"] = "#$idDoConteudo"
        attributes["id"] = idDoConteudo

        loading(id = id, isVertical = isVerticalLoading)
    }
}