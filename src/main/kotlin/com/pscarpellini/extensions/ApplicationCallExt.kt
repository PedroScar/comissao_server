package com.pscarpellini.extensions

import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
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

suspend fun ApplicationCall.respondToast(
    tipo: TiposToastEnum,
    mensagem: String,
) {
    this.response.headers.append("HX-Trigger", "toast-message")
    this.respondFragment { toast(mensagem = mensagem, tipo = tipo) }
}

suspend fun ApplicationCall.redirecionarFormHTMX(path: String) {
    this.response.headers.append("HX-Redirect", path)
    this.respond(HttpStatusCode.OK)
}