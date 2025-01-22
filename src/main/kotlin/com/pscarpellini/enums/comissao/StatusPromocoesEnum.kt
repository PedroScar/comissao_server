package com.pscarpellini.enums.comissao

enum class StatusPromocoesEnum(
    val nome: String,
    val slug: String,
) {
    ATIVA(
        nome = "Ativa",
        slug = "ativa"
    ),
    ENCERRADA(
        nome = "Encerrada",
        slug = "encerrada"
    ),
    AGENDADA(
        nome = "Agendada",
        slug = "agendada"
    );

    override fun toString() = slug
}