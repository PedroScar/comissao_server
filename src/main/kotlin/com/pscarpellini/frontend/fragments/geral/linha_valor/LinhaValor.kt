package com.pscarpellini.frontend.fragments.geral.linha_valor

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.linhaValor(
    classes: String = "",
    titulo: String,
    valor: String,
) {
    div(classes = classes) {
        h5 { +titulo }
        span (classes = CoresEnum.LOW_LIGHT.text) { +valor }
    }
}
