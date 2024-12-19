package com.pscarpellini.frontend.extensions

import com.pscarpellini.backend.models.dto.UserSession
import com.pscarpellini.frontend.exceptions.NaoLogadoException
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun RoutingContext.obterSessao(): UserSession {
    val sessao = call.sessions.get<UserSession>() ?: throw NaoLogadoException()
    return sessao
}