package com.pscarpellini.frontend.enums

enum class AlinhamentosEnum(
    val cssProprio: String,
) {
    START(cssProprio = "justify-start text-left"),
    CENTER(cssProprio = "justify-center text-center"),
    END(cssProprio = "justify-end text-right");

    override fun toString() = cssProprio
}