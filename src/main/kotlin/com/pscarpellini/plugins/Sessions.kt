package com.pscarpellini.plugins

import com.pscarpellini.models.vos.SessaoUsuarioVO
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import java.time.Duration

fun Application.configureSessions() {
    install(Sessions) {
        cookie<SessaoUsuarioVO>("user_session") {
            cookie.httpOnly = true
//            cookie.maxAgeInSeconds = Duration.ofMinutes(30).seconds
        }
    }
}
