package com.pscarpellini.frontend.enums

import com.pscarpellini.frontend.tailwind.PropriedadeTailwind

enum class ArredondamentosEnum(
    override val cssProprio: String,
    override val valor: String
): PropriedadeTailwind {
    NONE(cssProprio = "rounded-none", valor = "0"),
    SM(cssProprio = "rounded-sm", valor = "8px"),
    MD(cssProprio = "rounded-md", valor = "16px"),
    LG(cssProprio = "rounded-lg", valor = "24px"),
    PILL(cssProprio = "rounded-pill", valor = "9999px"),
}