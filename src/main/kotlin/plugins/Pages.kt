package model.plugins

import enums.PagesEnum
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.respondHtml
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import model.login.pages.loginPage
import style.styledRouting
import domain.comissaoPages

fun Application.configurePages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.OK) { }
    }

    comissaoPages()

    styledRouting {
        get(PagesEnum.Login.path) {
            val errorMessage = call.request.queryParameters["error"]
            call.respondHtml(HttpStatusCode.OK) { loginPage(errorMessage) }
        }
    }
}
