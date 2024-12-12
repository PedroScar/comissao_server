package com.pscarpellini.style

import baseBody
import baseBotaoArredondado
import baseHeader
import com.pscarpellini.pages.loginPage.loginPageStyle
import com.pscarpellini.pages.landingPage.landingPageStyle
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

@KtorDsl
fun Application.styledRouting(configuration: Routing.() -> Unit): RoutingRoot {
    val styledConfig: Routing.() -> Unit = {
        configuration()
        get("/styles.css") {
            call.respondCss {
                body { baseBody() }

                loginPageStyle()
                landingPageStyle()

                rule(".linearLayoutHorizontal") { linearLayoutHorizontal() }
                rule(".botao-vazado") { baseBotaoArredondado(Colors.transparent) }
                rule(".botao-verde") { baseBotaoArredondado(Colors.brand_pure) }

                rule(".landing-header") { baseHeader(Colors.neutral_high_pure) }
                rule(".login-header") { baseHeader(Colors.neutral_high_light) }

                rule(".header-logo") {
                    width = 145.px
                    height = 30.px
                }
            }
        }
    }
    return pluginOrNull(RoutingRoot)?.apply(styledConfig) ?: install(RoutingRoot, styledConfig)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}