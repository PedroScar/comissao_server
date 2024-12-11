package com.pscarpellini.style

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
import kotlinx.css.properties.TextDecoration

@KtorDsl
fun Application.styledRouting(configuration: Routing.() -> Unit): RoutingRoot {
    val styledConfig: Routing.() -> Unit = {
        configuration()
        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Colors.neutral_high_pure
                    margin = Margin(0.px)
                    padding = Padding(0.px)
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    minHeight = 100.vh
                    paddingTop = 108.px
                }

                rule(".landing-header") {
                    width = LinearDimension("calc(100% - 64px)")
                    backgroundColor = Colors.neutral_high_pure
                    padding = Padding(32.px)
                    position = Position.fixed
                    top = 0.px
                    zIndex = 100
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    justifyContent = JustifyContent.spaceBetween
                    alignItems = Align.center
                   // border = Border(1.px, BorderStyle.solid, Colors.neutral_low_pure)
                }

                rule(".header-logo") {
                    width = 145.px
                    height = 30.px
                }

                rule(".botoes-container") {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                }

                rule(".botao-especialista") {
                    padding = Padding(12.px, 24.px, 12.px, 24.px)
                    borderRadius = 9999.px
                    marginRight = 8.px
                    backgroundColor = Colors.neutral_high_light
                    color = Colors.neutral_low_pure
                    border = Border.none
                    cursor = Cursor.pointer
                    fontFamily = "Nunito"
                    fontSize = 16.px
                    fontWeight = FontWeight.w600
                    textAlign = TextAlign.left
                    textDecoration = TextDecoration.none
                    justifyContent = JustifyContent.center
                    display = Display.flex
                    alignItems = Align.center
                }

                rule(".botao-vazado") {
                    padding = Padding(12.px, 24.px, 12.px, 24.px)
                    backgroundColor = Colors.transparent
                    color = Colors.neutral_low_pure
                    border = Border.none
                    cursor = Cursor.pointer
                    fontFamily = "Nunito"
                    fontSize = 16.px
                    fontWeight = FontWeight.w600
                    textAlign = TextAlign.left
                    textDecoration = TextDecoration.none
                    justifyContent = JustifyContent.center
                    display = Display.flex
                    alignItems = Align.center
                }

                rule(".botao-entrar") {
                    padding = Padding(12.px, 24.px, 12.px, 24.px)
                    borderRadius = 9999.px
                    backgroundColor = Colors.brand_pure
                    color = Colors.neutral_low_pure
                    border = Border.none
                    cursor = Cursor.pointer
                    fontFamily = "Nunito"
                    fontSize = 16.px
                    fontWeight = FontWeight.w600
                    textAlign = TextAlign.left
                    textDecoration = TextDecoration.none
                    justifyContent = JustifyContent.center
                    display = Display.flex
                    alignItems = Align.center
                }


                rule(".conteudo-vertical-centralizado") {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    width = 100.pct
                    alignItems = Align.center
                    // border = Border(1.px, BorderStyle.solid, Colors.neutral_low_pure)
                }


                rule(".conteudo-vertical-start") {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    width = 100.pct
                    alignItems = Align.start
                    paddingTop = 64.px
                    // border = Border(1.px, BorderStyle.solid, Colors.neutral_low_pure)
                }

                rule(".imagem-demonstracao1") {
                    width = 100.pct
                    height = LinearDimension.auto
                }

                rule(".ic-whatsapp") {
                    width = 16.px
                    height = 16.px
                    marginLeft = 8.px
                }

                rule(".texto-landing-titulo") {
                    fontFamily = "Nunito"
                    fontSize = 56.px
                    fontWeight = FontWeight.w600
                    textAlign = TextAlign.center
                    color = Colors.neutral_low_pure
                    margin = Margin(64.px, 0.px, 0.px, 0.px)
                    padding = Padding(0.px)
                    whiteSpace = WhiteSpace.preLine
                }

                rule(".texto-landing-subtitulo") {
                    fontFamily = "Nunito"
                    fontSize = 24.px
                    fontWeight = FontWeight.w500
                    color = Colors.neutral_low_light
                    textAlign = TextAlign.center
                    margin = Margin(0.px)
                    padding = Padding(0.px)
                    whiteSpace = WhiteSpace.preLine
                }

                rule(".texto-landing-vantagens") {
                    fontFamily = "Nunito"
                    fontSize = 14.px
                    fontWeight = FontWeight.w600
                    color = Colors.brand_dark
                    textAlign = TextAlign.center
                    margin = Margin(0.px, 0.px, 0.px, 32.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-solucoes") {
                    fontFamily = "Nunito"
                    fontSize = 32.px
                    fontWeight = FontWeight.w600
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 32.px)
                    padding = Padding(0.px)
                }

                rule(".carrossel-landing") {
                    display = Display.flex
                    justifyContent = JustifyContent.spaceBetween
                    flexDirection = FlexDirection.row
                    width = LinearDimension("calc(100% - 64px)")
                    alignItems = Align.center
                    margin = Margin(32.px, 32.px, 0.px, 32.px)
                    overflow = Overflow.hidden
                }

                rule(".carrossel-item") {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    width = 290.px
                    alignItems = Align.start
                }

                rule(".carrossel-item2") {
                    display = Display.flex
                    width = 437.px
                    flexDirection = FlexDirection.column
                    alignItems = Align.start
                }

                rule(".imagem-carrossel") {
                    width = 290.px
                    height = LinearDimension.auto
                }

                rule(".texto-landing-carrossel-titulo") {
                    fontFamily = "Nunito"
                    fontSize = 18.px
                    fontWeight = FontWeight.w600
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(16.px, 0.px, 0.px, 0.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-carrossel-subtitulo") {
                    fontFamily = "Nunito"
                    fontSize = 16.px
                    fontWeight = FontWeight.w400
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.start
                    margin = Margin(8.px, 0.px, 0.px, 0.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-carrossel-titulo2") {
                    fontFamily = "Nunito"
                    fontSize = 80.px
                    fontWeight = FontWeight.w600
                    width = 437.px
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(0.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-carrossel-subtitulo2") {
                    fontFamily = "Nunito"
                    width = 437.px
                    fontSize = 22.px
                    fontWeight = FontWeight.w400
                    color = Colors.neutral_low_medium
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 0.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-porque-titulo") {
                    fontFamily = "Nunito"
                    fontSize = 14.px
                    fontWeight = FontWeight.w600
                    color = Colors.brand_dark
                    textAlign = TextAlign.center
                    margin = Margin(128.px, 0.px, 0.px, 32.px)
                    padding = Padding(0.px)
                }

                rule(".texto-landing-porque-subtitulo") {
                    fontFamily = "Nunito"
                    fontSize = 32.px
                    fontWeight = FontWeight.w600
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.center
                    margin = Margin(8.px, 0.px, 0.px, 32.px)
                    padding = Padding(0.px)
                }

                rule(".fundo-inferior") {
                    width = 100.pct
                    backgroundColor = Colors.brand_pure
                    flexDirection = FlexDirection.column
                    alignItems = Align.start
                    marginTop = 64.px
                    padding = Padding(32.px, 150.px, 32.px, 150.px)
                }

                rule(".img-logo-preto") {
                    width = 64.px
                    height = 64.px
                }

                rule(".texto-landing-inferior_lumen") {
                    fontFamily = "Nunito"
                    fontSize = 14.px
                    width = 400.px
                    fontWeight = FontWeight.w400
                    color = Colors.neutral_low_pure
                    textAlign = TextAlign.start
                    margin = Margin(24.px, 0.px, 0.px, 0.px)
                    padding = Padding(0.px)
                }

            }
        }
    }
    return pluginOrNull(RoutingRoot)?.apply(styledConfig) ?: install(RoutingRoot, styledConfig)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}