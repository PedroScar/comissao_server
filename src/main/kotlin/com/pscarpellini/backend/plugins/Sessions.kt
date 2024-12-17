package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.models.dto.UserSession
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.sessions.*

fun Application.configureSessions() {
    install(Sessions) {
        cookie<UserSession>("user_session") {
            cookie.httpOnly = true
        }
    }
}
