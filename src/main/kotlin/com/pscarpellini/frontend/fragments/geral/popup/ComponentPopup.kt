package com.pscarpellini.frontend.fragments.geral.popup

import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import kotlinx.html.*

/**
 * Exiba uma mensagem toast na página.
 */
fun FlowContent.popup(
    titulo: String,
    nome: String,
    showFechar: Boolean = true,
    conteudo: FlowContent.() -> Unit,
) {
    div(classes = "m-8 flex flex-col gap-4") {
        attributes["lm-popup-nome"] = nome
        attributes["hx-target"] = "#popup-content"
        attributes["hx-swap"] = "innerHTML"
        div(classes = "flex flex-row justify-between items-center") {
            h3(classes = "${CoresEnum.LOW_PURE.text} text-xl font-semibold") { +titulo }
            if(showFechar) botaoIcone(IconesEnum.CLOSE, tipo = TiposBotaoEnum.NEUTRAL, id = "close-popup")
        }
        conteudo()
    }
}