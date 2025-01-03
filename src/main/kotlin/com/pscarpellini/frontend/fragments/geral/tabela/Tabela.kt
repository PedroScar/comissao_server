package com.pscarpellini.frontend.fragments.geral.tabela

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.CoresEnum
import kotlinx.html.*

fun FlowContent.tabelaComHeadersFixos(
    headers: List<String>,
    linhas: List<List<FlowContent.() -> Unit>>,
    classes: String = ""
) {
    div(classes = "overflow-y-auto h-72 w-full") {
        table(classes = "table-auto ${ArredondamentosEnum.SM.cantosSuperiores} overflow-hidden w-full") {
            thead(classes = "${CoresEnum.HIGH_LIGHT.bg} border-b border-b-${CoresEnum.HIGH_MEDIUM}") {
                tr {
                    headers.forEach { header ->
                        th(classes = "sticky top-0 z-10 px-6 py-4 text-left text-sm font-semibold") {
                            +header
                        }
                    }
                }
            }
            tbody {
                linhas.forEach { linha ->
                    tr(classes = "even:${CoresEnum.HIGH_LIGHT} border-b border-b-${CoresEnum.HIGH_MEDIUM}") {
                        linha.forEach { celula ->
                            td(classes = "px-4 py-4") {
                                celula(this)
                            }
                        }
                    }
                }
            }
        }
    }
}