package com.pscarpellini.frontend.fragments.geral.formulario

import kotlinx.html.FlowContent
import kotlinx.html.FormMethod
import kotlinx.html.form

fun FlowContent.formulario(
    id: String,
    classes: String = "",
    autoValidar: Boolean = true,
    conteudo: FlowContent.() -> Unit,
) {
    form(classes = classes) {
        if (autoValidar) attributes["ktAutovalidate"] = ""
        attributes["id"] = id
        conteudo()
    }
}