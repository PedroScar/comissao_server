package com.pscarpellini.enums.comissao

import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum

enum class StatusPromocoesEnum(
    val nome: String,
    val slug: String,
    val tipoTag: TiposTagsEnum
) {
    ATIVA(
        nome = "Ativa",
        slug = "ativa",
        tipoTag = TiposTagsEnum.POSITIVE
    ),
    ENCERRADA(
        nome = "Encerrada",
        slug = "encerrada",
        tipoTag = TiposTagsEnum.DANGER
    ),
    CANCELADA(
        nome = "Cancelada",
        slug = "cancelada",
        tipoTag = TiposTagsEnum.DANGER
    ),
    AGENDADA(
        nome = "Agendada",
        slug = "agendada",
        tipoTag = TiposTagsEnum.WARNING
    );

    override fun toString() = slug
}