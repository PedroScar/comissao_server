package com.pscarpellini.extensions

import com.pscarpellini.rotas.PaginasRestritasEnum
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
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

suspend fun ApplicationCall.redirecionarFormHTMX(path: String) {
    this.response.headers.append("HX-Redirect", path)
    this.respond(HttpStatusCode.OK)
}