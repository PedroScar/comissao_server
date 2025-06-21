package com.pscarpellini.frontend.fragments.geral.tabela

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import kotlinx.html.*

fun FlowContent.tabelaComHeadersFixos(
    headers: List<String>,
    linhas: List<List<FlowContent.() -> Unit>>,
    classes: String = ""
) {
    div(classes = "overflow-y-auto w-full") {
        table(classes = "table-auto ${ArredondamentosEnum.SM.cantosSuperiores} overflow-hidden w-full") {
            thead(classes = "${CoresEnum.HIGH_LIGHT.bg} border-b border-b-${CoresEnum.HIGH_MEDIUM}") {
                tr {
                    headers.forEach { header ->
                        th(classes = "sticky top-0 z-10 px-4 py-4 text-center text-sm font-semibold") {
                            +header
                        }
                    }
                }
            }
            tbody {
                linhas.forEach { linha ->
                    tr(classes = "even:${CoresEnum.HIGH_LIGHT} border-b border-b-${CoresEnum.HIGH_MEDIUM} last:border-b-0") {
                        linha.forEach { celula ->
                            td(classes = "px-4 py-4 text-center") {
                                celula(this)
                            }
                        }
                    }
                }
            }
        }
    }
}