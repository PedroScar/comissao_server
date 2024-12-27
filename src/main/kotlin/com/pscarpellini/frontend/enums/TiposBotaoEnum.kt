package com.pscarpellini.frontend.enums

enum class TiposBotaoEnum(
    val cssProprio: String,
    val cssDesabilitado: String,
) {
    PRIMARY(
        cssProprio = "bg-brand-pure hover:bg-brand-medium rounded-pill text-low-pure",
        cssDesabilitado = "rounded-pill bg-high-dark text-low-light"
    ),
    NEUTRAL(
        cssProprio = "bg-high-light hover:bg-high-medium rounded-pill text-low-pure",
        cssDesabilitado = "rounded-pill bg-high-dark text-low-light"
    ),
    SUBTLE(
        cssProprio = "bg-none border border-transparent hover:border hover:border-high-dark rounded-pill text-low-pure",
        cssDesabilitado = "rounded-pill bg-high-dark text-low-light"
    ),
    WARNING_PRIMARY(
        cssProprio = "bg-alert-dark hover:bg-alert-pure rounded-md text-alert-light",
        cssDesabilitado = "rounded-md bg-high-dark text-low-light"
    ),
    WARNING_SUBTLE(
        cssProprio = "bg-transparent hover:bg-alert-light rounded-md text-alert-dark",
        cssDesabilitado = "rounded-md bg-high-dark text-low-light"
    ),
    TRANSPARENT(
        cssProprio = "bg-none text-low-pure hover:bg-high-light rounded-sm",
        cssDesabilitado = "rounded-pill bg-high-dark text-low-light"
    ),
}