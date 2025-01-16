package com.pscarpellini.frontend.enums.designsystem

enum class DirecoesEnum(
    val cssProprio: String,
) {
    HORIZONTAL(cssProprio = "flex flex-row items-center"),
    VERTICAL(cssProprio = "flex flex-col items-center"),
    NENHUM(cssProprio = ""),
}