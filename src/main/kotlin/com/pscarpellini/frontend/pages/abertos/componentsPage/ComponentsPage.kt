package com.pscarpellini.frontend.pages.abertos.componentsPage

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.fragments.geral.icone.TipoBotaoEnum
import com.pscarpellini.frontend.fragments.geral.button.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.div

fun HTML.componentsPage() {
    includeHtmlHeader()
    body(classes = "bg-high-light") {
        div(classes = "container flex flex-col gap-2") {
            card(arredondamento = ArredondamentosEnum.NONE, showBackground = false, showBordas = false) {
                botao(
                    tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.SM, showBackground = false, showBordas = true) {
                botao(
                    tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.MD, showBackground = true, showBordas = false) {
                botao(
                    tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.LG, showBackground = true, showBordas = true) {
                botao(
                    tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.PILL) {
                botao(
                    tipo = TipoBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
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
}