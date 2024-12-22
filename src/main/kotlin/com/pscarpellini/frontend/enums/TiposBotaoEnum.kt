package com.pscarpellini.frontend.enums

enum class TiposBotaoEnum(
    val cssProprio: String,
) {
    PRIMARY(cssProprio = "bg-brand-pure hover:bg-brand-medium rounded-pill text-low-pure"),
    NEUTRAL(cssProprio = "bg-high-light hover:bg-high-medium rounded-pill text-low-pure"),
    SUBTLE(cssProprio = "bg-none border border-transparent hover:border hover:border-high-dark rounded-pill text-low-pure"),

    WARNING_PRIMARY(cssProprio = "bg-alert-dark hover:bg-alert-pure rounded-md text-alert-light"),
    WARNING_SUBTLE(cssProprio = "bg-transparent hover:bg-alert-light rounded-md text-alert-dark"),
}