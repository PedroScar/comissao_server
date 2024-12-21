package com.pscarpellini.rotas

import com.pscarpellini.frontend.pages.abertos.componentsPage.componentsPage
import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.pages.abertos.loginPage.loginPage
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.models.vos.SessaoUsuarioVO
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasAbertas() {
    get(PaginasAbertasEnum.Landing.path) {
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
    get(PaginasAbertasEnum.Login.path) {
        call.sessions.set(SessaoUsuarioVO(nome = "Otávio Luiz"))
        call.respondHtml(HttpStatusCode.OK) { loginPage() }
    }
    get(PaginasAbertasEnum.Components.path) {
        call.respondHtml(HttpStatusCode.OK) { componentsPage() }
    }
}

enum class PaginasAbertasEnum(
    override val path: String
) : IPaginaEnum {
    Landing("/"),
    Login("/login"),
    Components("/components"),
}