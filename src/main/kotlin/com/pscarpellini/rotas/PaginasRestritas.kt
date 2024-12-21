package com.pscarpellini.rotas

import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.frontend.pages.abertos.landingPage.landingPage
import com.pscarpellini.frontend.pages.restritos.homePage
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.paginasRestritas() {
    get(PaginasRestritasEnum.Home.path) {
        val sessao = obterSessao()
        call.respondHtml(HttpStatusCode.OK) { homePage(sessao) }
    }
    get(PaginasRestritasEnum.Logout.path) {
        call.sessions.clear<SessaoUsuarioVO>()
        call.respondHtml(HttpStatusCode.OK) { landingPage() }
    }
}

enum class PaginasRestritasEnum(
    override val path: String
): IPaginaEnum {
    Home("/home"),
    AddUser("/addUser"),
    Logout("/logout"),
}