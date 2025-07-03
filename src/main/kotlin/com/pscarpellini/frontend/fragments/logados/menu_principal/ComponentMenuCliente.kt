package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.models.vos.ClienteVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

fun FlowContent.includeMenuCliente(
    cliente: ClienteVO?,
    classes: String = "",
) {
    div("flex flex-row items-center font-bold text-lg mb-4 gap-2 rounded-pill border border-high-medium px-2 py-2 $classes") {
        if(cliente?.logo != null) img(src = "data:image/png;base64, ${cliente.logo}", classes = "rounded-pill size-8")
        +(cliente?.nome ?: "Cliente")
    }
}