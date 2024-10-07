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
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.BoxShadows
import kotlinx.css.properties.TextDecoration

@KtorDsl
fun Application.styledRouting(configuration: Routing.() -> Unit): RoutingRoot {
    val styledConfig: Routing.() -> Unit = {
        configuration()
        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.gray
                }

                rule("*") {
                    boxSizing = BoxSizing.borderBox
                }

                rule("h1.page-title") {
                    color = Color.black
                    textAlign = TextAlign.center
                    fontWeight = FontWeight.bold
                }

                rule(".login-container") {
                    backgroundColor = Color.gray
                    display = Display.flex
                    justifyContent = JustifyContent.center
                    alignItems = Align.center
                    height =  100.vh
                }

                rule(".login-panel") {
                    width = 300.px
                    backgroundColor = Color.white
                    padding = Padding(20.px, 20.px, 20.px, 20.px)
                    borderRadius = 10.px
                    boxShadow = BoxShadows().apply {
                        this += BoxShadow(
                            color = Color.black,
                            blurRadius = 8.px,
                            spreadRadius = 0.px,
                            offsetX = 0.px,
                            offsetY = 4.px
                        )
                    }
                }

                rule(".form-main") {
                    width = LinearDimension.fillAvailable
                }

                rule(".form-input input") {
                    width = 100.pct
                    padding = Padding(10.px)
                    marginTop = 10.px
                    borderRadius = 5.px
                    border = Border(1.px, BorderStyle.solid, Color.black)
                }

                rule(".form-button button") {
                    width = 100.pct
                    padding = Padding(10.px)
                    marginTop = 20.px
                    backgroundColor = Color("#007BFF")
                    color = Color.white
                    border = Border.none
                    borderRadius = 5.px
                    cursor = Cursor.pointer
                }
                rule(".sidebar") {
                    width = 250.px
                    position = Position.fixed
                    top = 0.px
                    left = 0.px
                    height = 100.vh
                    backgroundColor = Color.black
                    overflowX = Overflow.hidden
                    paddingTop = 20.px
                }

                rule(".sidebar-item") {
                    padding = Padding(10.px, 15.px)
                    textDecoration = TextDecoration.none
                    fontSize = 25.px
                    color = Color.white
                    display = Display.block

                    hover {
                        backgroundColor = Color("#575757")
                    }
                }

                rule(".main-content") {
                    marginLeft = 250.px
                    padding = Padding(20.px)
                }
            }
        }
    }
    return pluginOrNull(RoutingRoot)?.apply(styledConfig) ?: install(RoutingRoot, styledConfig)
}

suspend inline fun ApplicationCall.respondCss(builder: CssBuilder.() -> Unit) {
    this.respondText(CssBuilder().apply(builder).toString(), ContentType.Text.CSS)
}