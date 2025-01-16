package com.pscarpellini.frontend.pages.abertos.componentes

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.icone.icone
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
                    tipo = TiposBotaoEnum.WARNING_PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.SM, showBackground = false, showBordas = true) {
                botao(
                    tipo = TiposBotaoEnum.WARNING_PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
                botao(
                    tipo = TiposBotaoEnum.WARNING_PRIMARY, small = false, enabled = true
                ) {
                    +"Acessar o app"
                    icone(icone = IconesEnum.APP, usarPadding = false)
                }
                botao(
                    tipo = TiposBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    icone(icone = IconesEnum.ARROW_LEFT)
                }
                botaoIcone(icone = IconesEnum.ARROW_LEFT)
                botaoIcone(icone = IconesEnum.ARROW_RIGHT)
                botaoIcone(icone = IconesEnum.AJUDA)



                icone(icone = IconesEnum.AJUDA, usarPadding = false)
                icone(icone = IconesEnum.INFO)
            }
            card(arredondamento = ArredondamentosEnum.MD, showBackground = true, showBordas = false) {
                botao(
                    tipo = TiposBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.LG, showBackground = true, showBordas = true) {
                botao(
                    tipo = TiposBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            card(arredondamento = ArredondamentosEnum.PILL) {
                botao(
                    tipo = TiposBotaoEnum.PRIMARY, small = false, enabled = true
                ) {
                    +"Button"
                }
            }
            botao(
                tipo = TiposBotaoEnum.PRIMARY, small = true, enabled = true
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.PRIMARY, small = false, enabled = false
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.PRIMARY, small = true, enabled = false
            ) {
                +"Button"
            }

            botao(
                tipo = TiposBotaoEnum.NEUTRAL, small = false, enabled = true
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.NEUTRAL, small = true, enabled = true
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.NEUTRAL, small = false, enabled = false
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.NEUTRAL, small = true, enabled = false
            ) {
                +"Button"
            }

            botao(
                tipo = TiposBotaoEnum.SUBTLE, small = false, enabled = true
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.SUBTLE, small = true, enabled = true
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.SUBTLE, small = false, enabled = false
            ) {
                +"Button"
            }
            botao(
                tipo = TiposBotaoEnum.SUBTLE, small = true, enabled = false
            ) {
                +"Button"
            }
        }
    }
}