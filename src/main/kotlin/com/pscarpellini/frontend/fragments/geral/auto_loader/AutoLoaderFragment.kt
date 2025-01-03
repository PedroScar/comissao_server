package com.pscarpellini.frontend.fragments.geral.auto_loader

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.autoLoaderFragment(
    id: String,
    path: String,
    classes: String = "",
) {
    val idDoConteudo = "$id-${System.currentTimeMillis()}"
    div(classes = classes) {
        attributes["hx-post"] = path
        attributes["hx-trigger"] = "load"
        attributes["hx-target"] = "#$idDoConteudo"
        attributes["id"] = idDoConteudo

        div(classes = "w-full h-full flex flex-col justify-center items-center") {
            icone(icone = IconesEnum.LOADER, classes = "animate-spin")
            +"Carregando"
        }
    }
}