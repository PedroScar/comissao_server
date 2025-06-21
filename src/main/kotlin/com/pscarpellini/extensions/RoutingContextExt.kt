package com.pscarpellini.extensions

import com.pscarpellini.acesso.UsuariosLogados
import com.pscarpellini.exceptions.NaoLogadoException
import com.pscarpellini.models.vos.CookieVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun RoutingContext.obterSessao(): SessaoUsuarioVO = run {
    val sessao = call.sessions.get<CookieVO>() ?: throw NaoLogadoException()

    UsuariosLogados.lista.find { usuarioLogado ->
        usuarioLogado.conta?.id == sessao.idUsuario &&
                usuarioLogado.conta?.cliente?.id == sessao.idCliente

    } ?: throw NaoLogadoException()
}


fun RoutingContext.fecharSessao() {
    val sessao = call.sessions.get<CookieVO>() ?: throw NaoLogadoException()
    UsuariosLogados.lista.removeIf { usuarioLogado ->
        usuarioLogado.conta?.id == sessao.idUsuario &&
                usuarioLogado.conta?.cliente?.id == sessao.idCliente
    }
    call.sessions.clear<CookieVO>()
}
