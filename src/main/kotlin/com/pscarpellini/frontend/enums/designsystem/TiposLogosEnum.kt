package com.pscarpellini.frontend.enums.designsystem

enum class TiposLogosEnum(
    val caminho: String,
) {
    NORMAL(caminho = "/static/logos/logo.svg"),
    BRANCA(caminho = "/static/logos/logo_branca.svg"),
    ESCRITA(caminho = "/static/logos/logo_escrita.svg"),
    ESCRITA_BRANCA(caminho = "/static/logos/logo_escrita_branca.svg"),
    ESCRITA_PRETA(caminho = "/static/logos/logo_escrita_preta.svg"),
    HORIZONTAL(caminho = "/static/logos/logo_horizontal.svg"),
    ICONE(caminho = "/static/logos/logo_icone.svg"),
    ICONE_BRANCA(caminho = "/static/logos/logo_icone_branca.svg"),
    ICONE_PRETA(caminho = "/static/logos/logo_icone_preta.svg"),
    PRETA(caminho = "/static/logos/logo_preta.svg");
}