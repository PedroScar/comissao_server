package com.pscarpellini.extensions

import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.exceptions.NaoLogadoException
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun RoutingContext.obterSessao(): SessaoUsuarioVO {
    val sessao = call.sessions.get<SessaoUsuarioVO>() ?: throw NaoLogadoException()
    println("====================================================================")
    println("Sessao ativa? ${sessao.isSessaoAtiva}")
    println("Data de acesso ${sessao.dataDeAcesso}")
    println("Validade da sessão ${sessao.dataDeExpiracao}")
    println("====================================================================")
    if(!sessao.isSessaoAtiva) throw NaoLogadoException()
    sessao.aumentarPrazoDeExpiracao()
    return sessao
}