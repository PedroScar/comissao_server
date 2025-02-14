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
    fecharPopupAberto: Boolean = false,
    fragment: BODY.() -> Unit
) {
    if(fecharPopupAberto) fecharPopup()
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

suspend fun ApplicationCall.respondPopup(
    fragment: BODY.() -> Unit
) {
    this.response.headers.append("HX-Trigger", "lm-popup-open")
    this.response.headers.append("HX-Retarget", "#popup-content")
    this.respondFragment(fecharPopupAberto = false) { fragment() }
}

fun ApplicationCall.fecharPopup(): ApplicationCall {
    this.response.headers.append("HX-Trigger", "lm-popup-close")
    return this
}

suspend fun ApplicationCall.redirecionarFormHTMX(path: String) {
    this.response.headers.append("HX-Redirect", path)
    this.respond(HttpStatusCode.OK)
}