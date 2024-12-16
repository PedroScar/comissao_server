package com.pscarpellini.frontend.style

import baseBody
import baseBotaoArredondado
import com.pscarpellini.frontend.pages.landingPage.landingPageStyle
import com.pscarpellini.frontend.pages.loginPage.loginPageStyle
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