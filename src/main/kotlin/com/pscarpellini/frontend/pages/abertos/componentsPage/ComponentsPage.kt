package com.pscarpellini.frontend.pages.abertos.componentsPage

import com.pscarpellini.frontend.fragments.geral.button.TipoBotaoEnum
import com.pscarpellini.frontend.fragments.geral.button.botao
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import kotlinx.html.HTML
import kotlinx.html.body

fun HTML.componentsPage() {
    includeHtmlHeader()
    body {
        botao(
            tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.PRIMARY, small = true, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = false
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.PRIMARY, small = true, enabled = false
        ) {
            +"Button"
        }

        botao(
            tipo = TipoBotaoEnum.NEUTRAL, small = false, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.NEUTRAL, small = true, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.NEUTRAL, small = false, enabled = false
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.NEUTRAL, small = true, enabled = false
        ) {
            +"Button"
        }

        botao(
            tipo = TipoBotaoEnum.SUBTLE, small = false, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.SUBTLE, small = true, enabled = true
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.SUBTLE, small = false, enabled = false
        ) {
            +"Button"
        }
        botao(
            tipo = TipoBotaoEnum.SUBTLE, small = true, enabled = false
        ) {
            +"Button"
        }
    }
}