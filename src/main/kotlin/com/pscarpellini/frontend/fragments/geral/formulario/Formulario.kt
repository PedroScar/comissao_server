package com.pscarpellini.frontend.fragments.geral.formulario

import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.divider.divider
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.interfaces.IPaginaEnum
import kotlinx.html.*

fun FlowContent.formulario(
    id: String,
    classes: String = "",
    autoValidar: Boolean = true,
    conteudo: FlowContent.() -> Unit,
) {
    form(classes = classes) {
        if(autoValidar) attributes["ktAutovalidate"] = ""
        attributes["id"] = id
        conteudo()
    }
}