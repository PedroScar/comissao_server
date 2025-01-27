package com.pscarpellini.frontend.fragments.geral.loading

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.loading(
    id: String,
    usarDiferenciadorId: Boolean = true,
    textoLoading: String = "Carregando",
    isVertical: Boolean = false
): String {
    val idDoConteudo = if(usarDiferenciadorId) "$id-${System.currentTimeMillis()}" else id
    div(classes = "w-full h-full flex ${if(isVertical) "flex-col" else "flex-row"} justify-center items-center cursor-progress") {
        attributes["id"] = idDoConteudo
        icone(icone = IconesEnum.LOADER, classes = "animate-spin")
        +textoLoading
    }
    return idDoConteudo
}