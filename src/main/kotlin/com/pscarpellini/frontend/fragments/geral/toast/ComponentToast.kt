package com.pscarpellini.frontend.fragments.geral.toast

import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.script
import kotlinx.html.unsafe

/**
 * Exiba uma mensagem toast na página.
 */
fun FlowContent.toast(
    mensagem: String,
    tipo: TiposToastEnum = TiposToastEnum.SUCCESS,
) {
    div {
        attributes["lm-toast-tipo"] = tipo.nome
        attributes["lm-toast-mensagem"] = mensagem
    }
}