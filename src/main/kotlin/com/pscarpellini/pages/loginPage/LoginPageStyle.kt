package com.pscarpellini.pages.loginPage

import baseTexto
import com.pscarpellini.style.Colors
import gravityStart
import kotlinx.css.CssBuilder
import kotlinx.css.FontWeight
import kotlinx.css.Margin
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.fontSize
import kotlinx.css.fontWeight
import kotlinx.css.gap
import kotlinx.css.margin
import kotlinx.css.marginTop
import kotlinx.css.px
import kotlinx.css.width
import linearLayoutHorizontal
import linearLayoutVertical
import preencherTelaRestante

fun CssBuilder.loginPageStyle() {
    rule(".linearLayoutHorizontal-login") {
        linearLayoutHorizontal()
        gap = 8.px
    }

    rule(".texto-login-duvida") {
        baseTexto()
        fontSize = 16.px
        fontWeight = FontWeight.w400
        color = Colors.neutral_low_pure
        margin = Margin(0.px)
    }

    rule(".botao-login-contato") {
        baseTexto()
        fontSize = 16.px
        fontWeight = FontWeight.w600
        color = Colors.neutral_low_pure
    }

    rule(".conteudo-vertical-background-cinza") {
        linearLayoutVertical(screenWidth = true)
        preencherTelaRestante()
        marginTop = 94.px
        backgroundColor = Colors.neutral_high_light
    }

    rule(".carrossel-item2") {
        linearLayoutVertical()
        gravityStart()
        width = 437.px
    }
}