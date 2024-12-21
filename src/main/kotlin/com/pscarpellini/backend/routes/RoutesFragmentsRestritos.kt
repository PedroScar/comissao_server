package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.routes.FragmentsRestritosEnum
import com.pscarpellini.frontend.extensions.obterSessao
import com.pscarpellini.frontend.extensions.respondFragment
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import io.ktor.server.routing.*

fun Routing.fragmentsRestritos() {
    post(FragmentsRestritosEnum.Menu.path) {
        call.respondFragment { includeMenuPrincipal() }
    }
    post(FragmentsRestritosEnum.HeaderDashboard.path) {
        val sessao = obterSessao()
        call.respondFragment { includeHeaderLogado (sessao) }
    }
}