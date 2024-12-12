package com.pscarpellini.style

import gravityCenter
import gravityStart
import baseBody
import baseBotaoArredondado
import baseHeader
import baseTexto
import io.ktor.http.ContentType
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.application.pluginOrNull
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.get
import io.ktor.utils.io.KtorDsl
import kotlinx.css.*
import kotlinx.css.width
import linearLayoutHorizontal
import linearLayoutVertical
import preencherTelaRestante
import screenWidth

@KtorDsl
fun Application.styledRouting(configuration: Routing.() -> Unit): RoutingRoot {

    val styledConfig: Routing.() -> Unit = {
        configuration()
        get("/styles.css") {
            call.respondCss {
                body { baseBody() }

                rule(".linearLayoutHorizontal") { linearLayoutHorizontal() }
                rule(".botao-especialista") { baseBotaoArredondado(Colors.neutral_high_light); marginRight = 8.px }
                rule(".botao-vazado") { baseBotaoArredondado(Colors.transparent) }
                rule(".botao-verde") { baseBotaoArredondado(Colors.brand_pure) }

                rule(".landing-header") { baseHeader(Colors.neutral_high_pure) }
                rule(".login-header") { baseHeader(Colors.neutral_high_light) }

                rule(".header-logo") {
                    width = 145.px
                    height = 30.px
                }

                rule(".conteudo-vertical-centralizado") {
                    linearLayoutVertical(screenWidth = true)
                    gravityCenter()
                    marginTop = 108.px
                }

                rule(".conteudo-vertical-background-cinza") {
                    linearLayoutVertical(screenWidth = true)
                    preencherTelaRestante()
                    marginTop = 94.px
                    backgroundColor = Colors.neutral_high_light
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

                rule(".ic-whatsapp") {
                    width = 16.px
                    height = 16.px
                    marginLeft = 8.px
                }

                rule(".texto-landing-titulo") {
                    baseTexto()
                    fontSize = 56.px
                    fontWeight = FontWeight.w600
                    textAlign = TextAlign.center
                    color = Colors.neutral_low_pure
                    margin = Margin(64.px, 0.px, 0.px, 0.px)
                    whiteSpace = WhiteSpace.preLine
                }

                rule(".texto-landing-subtitulo") {
                    baseTexto()
                    fontSize = 24.px
                    fontWeight = FontWeight.w500
                    color = Colors.neutral_low_light
                    textAlign = TextAlign.center
                    margin = Margin(0.px)
                    whiteSpace = WhiteSpace.preLine
                }

                rule(".texto-landing-vantagens") {
                    baseTexto()
                    fontSize = 14.px
                    fontWeight = FontWeight.w600
                    color = Colors.brand_dark
                    textAlign = TextAlign.center
                    margin = Margin(0.px, 0.px, 0.px, 32.px)
                }

                rule(".texto-landing-solucoes") {
                    baseTexto()
                    fontSize = 32.px
                    fontWeight = FontWeight.w600
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 32.px)
                }

                rule(".carrossel-landing") {
                    linearLayoutHorizontal(true)
                    width = LinearDimension("calc(100% - 64px)")
                    margin = Margin(32.px, 32.px, 0.px, 32.px)
                    overflow = Overflow.hidden
                }

                rule(".carrossel-item") {
                    linearLayoutVertical()
                    gravityStart()
                    width = 290.px
                }

                rule(".carrossel-item2") {
                    linearLayoutVertical()
                    gravityStart()
                    width = 437.px
                }

                rule(".imagem-carrossel") {
                    width = 290.px
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
                    fontSize = 80.px
                    fontWeight = FontWeight.w600
                    width = 437.px
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(0.px)
                }

                rule(".texto-landing-carrossel-subtitulo2") {
                    baseTexto()
                    width = 437.px
                    fontSize = 22.px
                    fontWeight = FontWeight.w400
                    color = Colors.neutral_low_medium
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 0.px)
                }

                rule(".texto-landing-porque-titulo") {
                    baseTexto()
                    fontSize = 14.px
                    fontWeight = FontWeight.w600
                    color = Colors.brand_dark
                    margin = Margin(128.px, 0.px, 0.px, 32.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-porque-subtitulo") {
                    baseTexto()
                    fontSize = 32.px
                    fontWeight = FontWeight.w600
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 32.px)
                }

                rule(".fundo-inferior") {
                    linearLayoutVertical(screenWidth = true)
                    gravityStart()
                    backgroundColor = Colors.brand_pure
                    marginTop = 64.px
                    padding = Padding(32.px, 150.px, 32.px, 150.px)
                }

                rule(".img-logo-preto") {
                    width = 64.px
                    height = 64.px
                }

                rule(".texto-landing-inferior_lumen") {
                    baseTexto()
                    fontSize = 14.px
                    width = 400.px
                    fontWeight = FontWeight.w400
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.start
                    margin = Margin(24.px, 0.px, 0.px, 0.px)
                }


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
            }
        }
    }
    return pluginOrNull(RoutingRoot)?.apply(styledConfig) ?: install(RoutingRoot, styledConfig)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}