package com.pscarpellini.frontend.enums.designsystem

import com.pscarpellini.extensions.formatarNomeTailwind
import com.pscarpellini.frontend.tailwind.PropriedadeTailwind

enum class ArredondamentosEnum(
    override val valor: String
): PropriedadeTailwind {
    NONE(valor = "0"),
    SM(valor = "8px"),
    MD(valor = "16px"),
    LG(valor = "24px"),
    PILL(valor = "9999px");

    override val cssProprio: String
        get() = "rounded-${this.name.formatarNomeTailwind()}"

    val cantoEsquerdoSuperior: String
        get() = "rounded-tl-${this.name.formatarNomeTailwind()}"
    val cantoEsquerdoInferior: String
        get() = "rounded-bl-${this.name.formatarNomeTailwind()}"
    val cantoDireitoSuperior: String
        get() = "rounded-tr-${this.name.formatarNomeTailwind()}"
    val cantoDireitoInferior: String
        get() = "rounded-br-${this.name.formatarNomeTailwind()}"
    val cantosSuperiores: String
        get() = "rounded-t-${this.name.formatarNomeTailwind()}"
    val cantosInferiores: String
        get() = "rounded-b-${this.name.formatarNomeTailwind()}"

    override fun toString() = this.cssProprio
}