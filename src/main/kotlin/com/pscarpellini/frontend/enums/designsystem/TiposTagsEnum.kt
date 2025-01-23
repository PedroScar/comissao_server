package com.pscarpellini.frontend.enums.designsystem

enum class TiposTagsEnum(
    val cssProprio: String,
    val cssSecundaria: String
) {
    BRAND(
        cssProprio = "${CoresEnum.BRAND_PURE.bg} hover:${CoresEnum.BRAND_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text}",
        cssSecundaria = "${CoresEnum.BRAND_LIGHT.bg} hover:${CoresEnum.BRAND_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.BRAND_PURE.text}"
    ),
    DANGER(
        cssProprio = "${CoresEnum.ALERT_DARK.bg} hover:${CoresEnum.ALERT_PURE.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.ALERT_LIGHT.text}",
        cssSecundaria = "${CoresEnum.ALERT_LIGHT.bg} hover:${CoresEnum.ALERT_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.ALERT_DARK.text}"
    ),
    POSITIVE(
        cssProprio = "${CoresEnum.SUCCESS_DARK.bg} hover:${CoresEnum.SUCCESS_PURE.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.SUCCESS_LIGHT.text}",
        cssSecundaria = "${CoresEnum.SUCCESS_LIGHT.bg} hover:${CoresEnum.SUCCESS_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.SUCCESS_DARK.text}"
    ),
    WARNING(
        cssProprio = "${CoresEnum.WARNING_PURE.bg} hover:${CoresEnum.WARNING_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text}",
        cssSecundaria = "${CoresEnum.WARNING_LIGHT.bg} hover:${CoresEnum.WARNING_MEDIUM.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.WARNING_DARK.text}"
    ),
    NEUTRAL(
        cssProprio = "${CoresEnum.HIGH_MEDIUM.bg} hover:${CoresEnum.HIGH_LIGHT.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.LOW_PURE.text}",
        cssSecundaria = "${CoresEnum.HIGH_LIGHT.bg} hover:${CoresEnum.HIGH_LIGHT.bg} ${ArredondamentosEnum.PILL} ${CoresEnum.HIGH_MEDIUM.text}"
    );

    companion object {
        fun obterEnumPeloNome(nome: String) = TiposTagsEnum.entries.firstOrNull { it.name == nome } ?: BRAND
    }
}