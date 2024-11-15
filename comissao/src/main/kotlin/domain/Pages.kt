package domain

import style.styledRouting
import enums.PagesEnum
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import pages.comissaoAddUserPage

fun Application.comissaoPages() {
    styledRouting {

        get(PagesEnum.AddUser.path) {
            call.respondHtml(HttpStatusCode.OK) { comissaoAddUserPage() }
        }
    }
}
