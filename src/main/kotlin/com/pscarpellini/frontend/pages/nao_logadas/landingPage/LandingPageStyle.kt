package com.pscarpellini.frontend.pages.nao_logadas.landingPage

import baseBotaoArredondado
import baseHeader
import baseTexto
import com.pscarpellini.frontend.style.Colors
import gravityCenter
import gravityStart
import kotlinx.css.*
import linearLayoutHorizontal
import linearLayoutVertical
import screenWidth

fun CssBuilder.landingPageStyle() {

    rule(".landing-header") { baseHeader(Colors.neutral_high_pure) }

    rule(".conteudo-vertical-centralizado") {
        linearLayoutVertical(screenWidth = true)
        gravityCenter()
        backgroundColor = Colors.neutral_high_pure
        marginTop = 108.px
    }

    rule(".conteudo-vertical-start") {
        linearLayoutVertical(screenWidth = true)
        gravityStart()
        paddingTop = 64.px
    }

    rule(".imagem-demonstracao1") {
        screenWidth()
        height = LinearDimension.auto
    }

    rule(".botao-especialista") {
        baseBotaoArredondado(Colors.neutral_high_light)
    }

    rule(".ic-whatsapp") {
        width = 16.px
        height = 16.px
        marginLeft = 8.px
    }

    rule(".texto-landing-titulo") {
        baseTexto()
        fontWeight = FontWeight.w600
        textAlign = TextAlign.center
        color = Colors.neutral_low_pure
        margin = Margin(64.px, 0.px, 0.px, 0.px)
        whiteSpace = WhiteSpace.preLine
    }

    rule(".texto-landing-subtitulo") {
        baseTexto()
        fontWeight = FontWeight.w500
        color = Colors.neutral_low_light
        textAlign = TextAlign.center
        whiteSpace = WhiteSpace.preLine
    }

    rule(".carrossel-landing") {
        linearLayoutHorizontal(true)
        margin = Margin(32.px, 32.px, 0.px, 32.px)
        overflow = Overflow.hidden
    }

    rule(".carrossel-item") {
        linearLayoutVertical()
        gravityStart()
    }

    rule(".imagem-carrossel") {
        height = LinearDimension.auto
    }


    rule(".texto-landing-carrossel-titulo") {
        baseTexto()
        fontSize = 18.px
        fontWeight = FontWeight.w600
        color = Colors.neutral_low_pure
        textAlign = TextAlign.center
        margin = Margin(16.px, 0.px, 0.px, 0.px)
    }

    rule(".texto-landing-carrossel-subtitulo") {
        baseTexto()
        fontSize = 16.px
        fontWeight = FontWeight.w400
        color = Colors.neutral_low_pure
        textAlign = TextAlign.start
        margin = Margin(8.px, 0.px, 0.px, 0.px)
    }

    rule(".texto-landing-carrossel-titulo2") {
        baseTexto()
        fontWeight = FontWeight.w600
        color = Colors.neutral_low_pure
        textAlign = TextAlign.center
    }

    rule(".texto-landing-carrossel-subtitulo2") {
        baseTexto()
        fontSize = 22.px
        fontWeight = FontWeight.w400
        color = Colors.neutral_low_medium
        textAlign = TextAlign.center
        margin = Margin(8.px, 0.px, 0.px, 0.px)
    }

    rule(".texto-landing-titulo-section") {
        baseTexto()
        fontSize = 14.px
        fontWeight = FontWeight.w600
        color = Colors.brand_dark
        margin = Margin(3.rem, 0.px, 0.px, 32.px)
    }

    rule(".texto-landing-subtitulo-section") {
        baseTexto()
        fontSize = 32.px
        fontWeight = FontWeight.w600
        color = Colors.neutral_low_pure
        margin = Margin(8.px, 0.px, 0.px, 32.px)
    }

    rule(".img-logo-preto") {
        width = 64.px
        height = 64.px
    }

    rule(".texto-landing-inferior_lumen") {
        baseTexto()
        fontSize = 14.px
        fontWeight = FontWeight.w400
        color = Colors.neutral_low_pure
        textAlign = TextAlign.start
    }
}