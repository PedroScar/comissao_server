package com.pscarpellini.frontend.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import kotlinx.html.BODY
import kotlinx.html.body

suspend fun ApplicationCall.respondFragment(
    status: HttpStatusCode = HttpStatusCode.OK,
    fragment: BODY.() -> Unit
) {
    this.respondHtml(status) {
        body { fragment.invoke(this) }
    }
}