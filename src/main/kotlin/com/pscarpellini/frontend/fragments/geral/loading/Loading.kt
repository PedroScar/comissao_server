package com.pscarpellini.frontend.fragments.geral.loading

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.loading(
    id: String,
    isVertical: Boolean = false
): String {
    val idDoConteudo = "$id-${System.currentTimeMillis()}"
    div(classes = "w-full h-full flex ${if(isVertical) "flex-col" else "flex-row"} justify-center items-center") {
        attributes["id"] = idDoConteudo
        icone(icone = IconesEnum.LOADER, classes = "animate-spin")
        +"Carregando"
    }
    return idDoConteudo
}