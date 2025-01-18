package com.pscarpellini.frontend.enums.designsystem

enum class TiposBotaoEnum(
    val cssProprio: String,
) {
    PRIMARY(
        cssProprio = "${CoresEnum.BRAND_PURE.bg} hover:${CoresEnum.BRAND_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text} disabled:${ArredondamentosEnum.PILL} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
    NEUTRAL(
        cssProprio = "${CoresEnum.HIGH_LIGHT.bg} hover:${CoresEnum.HIGH_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text} disabled:${ArredondamentosEnum.PILL} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
    SUBTLE(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} border ${CoresEnum.TRANSPARENT.border} hover:border hover:${CoresEnum.HIGH_DARK.border} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text} disabled:${ArredondamentosEnum.PILL} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
    WARNING_PRIMARY(
        cssProprio = "${CoresEnum.ALERT_DARK.bg} hover:${CoresEnum.ALERT_PURE.bg} ${ArredondamentosEnum.MD} ${CoresEnum.ALERT_LIGHT.text} disabled:${ArredondamentosEnum.MD} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
    WARNING_SUBTLE(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} hover:${CoresEnum.ALERT_LIGHT.bg} ${ArredondamentosEnum.MD} ${CoresEnum.ALERT_DARK.text} disabled:${ArredondamentosEnum.MD} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
    TRANSPARENT(
        cssProprio = "${CoresEnum.TRANSPARENT.bg} ${CoresEnum.LOW_PURE.text} hover:${CoresEnum.HIGH_LIGHT.bg} ${ArredondamentosEnum.SM} disabled:${ArredondamentosEnum.PILL} disabled:${CoresEnum.HIGH_DARK.bg} disabled:${CoresEnum.LOW_LIGHT.text}"
    ),
}