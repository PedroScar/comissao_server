package com.pscarpellini.extensions

import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.exceptions.NaoLogadoException
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun RoutingContext.obterSessao(): SessaoUsuarioVO {
    val sessao = call.sessions.get<SessaoUsuarioVO>() ?: throw NaoLogadoException()
    return sessao
}