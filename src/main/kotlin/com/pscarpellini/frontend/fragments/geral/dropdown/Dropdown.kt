package com.pscarpellini.frontend.fragments.geral.dropdown

import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.divider.divider
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.interfaces.IPaginaEnum
import kotlinx.html.*

fun FlowContent.dropdown(
    botao: FlowContent.() -> Unit,
    showChevron: Boolean = true,
    posicao: PosicoesDropdownEnum = PosicoesDropdownEnum.ESQUERDA,
    opcoes: ArrayList<DropdownElement> = arrayListOf()
) {
    nav(classes = "relative inline-block text-left group") {
        attributes["ktDropdown"] = ""
        button(classes = "flex flex-row items-center justify-center") {
            id = "dropdownButton"
            botao()
            if(opcoes.isNotEmpty() && showChevron) dropdownChevron()
        }
        if(opcoes.isNotEmpty()) {
            div(classes = "p-2 ${CoresEnum.HIGH_PURE.bg} ${ArredondamentosEnum.SM} shadow-lg min-w-56 z-50 font-semibold border ${CoresEnum.HIGH_DARK.border} hidden group-hover:flex flex-col gap-2 $posicao") {
                id = "dropdownMenu"
                opcoes.forEach { opcao ->
                    when(opcao) {
                        is DropdownDivider -> divider()
                        is DropdownItem -> dropdownItem(nome = opcao.nome, link = opcao.link, corTexto = opcao.corTexto)
                        is DropdownItemLink -> dropdownItemLink(nome = opcao.nome, link = opcao.link, corTexto = opcao.corTexto)
                    }
                }
            }
        }
    }
}

private fun FlowContent.dropdownChevron() {
    icone(IconesEnum.CHEVRON_DOWN, usarPadding = false, classes = "group-hover:rotate-180 transition-all duration-300")
}

private fun FlowContent.dropdownItemLink(nome: String, link: IPaginaEnum, corTexto: CoresEnum) {
    a(
        classes = "${CoresEnum.TRANSPARENT.bg} hover:${CoresEnum.HIGH_LIGHT.bg} rounded-sm font-semibold text-$corTexto py-2 px-6 cursor-pointer flex flex-row items-center ${AlinhamentosEnum.START} transition-all duration-300",
        href = link.path
    ) { +nome }
}

private fun FlowContent.dropdownItem(nome: String, link: IPaginaEnum, corTexto: CoresEnum) {
    div(
        "${CoresEnum.TRANSPARENT.bg} hover:${CoresEnum.HIGH_LIGHT.bg} rounded-sm font-semibold text-$corTexto py-2 px-6 cursor-pointer flex flex-row items-center ${AlinhamentosEnum.START} transition-all duration-300"
    ) {
        attributes["hx-post"] = link.path
        attributes["hx-trigger"] = "click"
        attributes["hx-target"] = "#conteudo-interno"
        attributes["hx-replace-url"] = link.path
        attributes["hx-swap"] = "innerHTML"
        +nome
    }
}

interface DropdownElement
class DropdownDivider : DropdownElement
class DropdownItem(
    val nome: String,
    val link: IPaginaEnum,
    val corTexto: CoresEnum = CoresEnum.LOW_PURE
) : DropdownElement
class DropdownItemLink(
    val nome: String,
    val link: IPaginaEnum,
    val corTexto: CoresEnum = CoresEnum.LOW_PURE
) : DropdownElement