package com.pscarpellini.plugins

import com.pscarpellini.enums.PagesEnum
import com.pscarpellini.pages.homePage
import com.pscarpellini.pages.loginPage
import com.pscarpellini.style.styledRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.respondHtml
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.OK) { }
    }

    styledRouting {
        get(PagesEnum.Login.path) {
            val errorMessage = call.request.queryParameters["error"]
            call.respondHtml(HttpStatusCode.OK) { loginPage(errorMessage) }
        }

        get(PagesEnum.Home.path) {
            val errorMessage = call.request.queryParameters["error"]
            call.respondHtml(HttpStatusCode.OK) {
                homePage(errorMessage)
            }
        }

        get(PagesEnum.AddUser.path) {
            call.respondHtml(HttpStatusCode.OK, PagesEnum.AddUser.reference)
        }
    }
}
