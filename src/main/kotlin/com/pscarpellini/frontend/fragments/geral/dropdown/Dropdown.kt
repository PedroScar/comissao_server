package com.pscarpellini.frontend.fragments.geral.dropdown

import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.css.button
import kotlinx.css.div
import kotlinx.html.*

fun FlowContent.dropdown(
    botao: FlowContent.() -> Unit,
    showChevron: Boolean = true,
    posicao: PosicoesDropdownEnum = PosicoesDropdownEnum.ESQUERDA,
    dropdown: FlowContent.() -> Unit = {}
) {
    nav(classes = "relative inline-block text-left group") {
        attributes["ktDropdown"] = ""
        button(classes = "flex flex-row items-center justify-center") {
            id = "dropdownButton"
            botao()
            if(showChevron) dropdownChevron()
        }
        div(classes = "p-2 bg-${CoresEnum.HIGH_PURE} ${ArredondamentosEnum.SM} shadow-lg min-w-56 z-50 font-semibold border border-high-dark hidden group-hover:block flex flex-row $posicao") {
            id = "dropdownMenu"
            dropdown(this)
        }
    }
}

private fun FlowContent.dropdownChevron() {
    icone(IconesEnum.CHEVRON_DOWN, usarPadding = false, classes = "group-hover:rotate-180 transition-all duration-300")
}

fun FlowContent.dropdownItem(nome: String, link: String, corTexto: CoresEnum = CoresEnum.LOW_PURE) {
    a(
        classes = "bg-none hover:bg-high-light rounded-sm font-semibold text-$corTexto py-2 px-6 cursor-pointer flex flex-row items-center ${AlinhamentosEnum.START} transition-all duration-300",
        href = link
    ) { +nome }
}