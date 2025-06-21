package com.pscarpellini.plugins

import com.pscarpellini.models.vos.CookieVO
import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSessions() {
    install(Sessions) {
        cookie<CookieVO>("user_session") {
            cookie.httpOnly = true
        }
    }
}
