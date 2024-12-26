package com.pscarpellini.frontend.enums

enum class TiposToastEnum(
    val corFundo: CoresEnum,
    val corTexto: CoresEnum,
    val duracao: Int
) {
    DEFAULT(corFundo = CoresEnum.HIGH_DARK, corTexto = CoresEnum.LOW_PURE, duracao = 5),
    ALERT(corFundo = CoresEnum.ALERT_MEDIUM, corTexto = CoresEnum.ALERT_DARK, duracao = 5),
    WARNING(corFundo = CoresEnum.WARNING_MEDIUM, corTexto = CoresEnum.WARNING_DARK, duracao = 5),
    SUCCESS(corFundo = CoresEnum.SUCCESS_MEDIUM, corTexto = CoresEnum.SUCCESS_DARK, duracao = 5),
}