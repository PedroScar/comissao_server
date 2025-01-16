package com.pscarpellini.frontend.enums.designsystem

enum class PosicoesDropdownEnum(
    val cssProprio: String
) {
    ESQUERDA(cssProprio = "absolute left-0"),
    MEIO(cssProprio = "absolute left-0 right-0"),
    DIREITA(cssProprio = "absolute right-0");

    override fun toString() = this.cssProprio
}