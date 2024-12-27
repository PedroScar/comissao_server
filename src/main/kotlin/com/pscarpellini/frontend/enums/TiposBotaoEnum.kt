package com.pscarpellini.frontend.enums

enum class TiposBotaoEnum(
    val cssProprio: String,
    val cssDesabilitado: String,
) {
    PRIMARY(
        cssProprio = "${CoresEnum.BRAND_PURE.bg} hover:${CoresEnum.BRAND_MEDIUM.bg} rounded-pill ${CoresEnum.LOW_PURE.text}",
        cssDesabilitado = "rounded-pill ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
    NEUTRAL(
        cssProprio = "${CoresEnum.HIGH_LIGHT.bg} hover:${CoresEnum.HIGH_MEDIUM.bg} rounded-pill ${CoresEnum.LOW_PURE.text}",
        cssDesabilitado = "rounded-pill ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
    SUBTLE(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} border ${CoresEnum.TRANSPARENT.border} hover:border hover:${CoresEnum.HIGH_DARK.border} rounded-pill ${CoresEnum.LOW_PURE.text}",
        cssDesabilitado = "rounded-pill ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
    WARNING_PRIMARY(
        cssProprio = "${CoresEnum.ALERT_DARK.bg} hover:${CoresEnum.ALERT_PURE.bg} rounded-md ${CoresEnum.ALERT_LIGHT.text}",
        cssDesabilitado = "rounded-md ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
    WARNING_SUBTLE(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} hover:${CoresEnum.ALERT_LIGHT.bg} rounded-md ${CoresEnum.ALERT_DARK.text}",
        cssDesabilitado = "rounded-md ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
    TRANSPARENT(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} ${CoresEnum.LOW_PURE.text} hover:${CoresEnum.HIGH_LIGHT.bg} rounded-sm",
        cssDesabilitado = "rounded-pill ${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text}"
    ),
}