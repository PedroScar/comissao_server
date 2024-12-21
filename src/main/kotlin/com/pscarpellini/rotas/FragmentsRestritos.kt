package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import io.ktor.server.routing.*
import kotlinx.html.FormMethod

fun Route.fragmentsRestritos() {
    post(FragmentsRestritosEnum.Menu.path) {
        call.respondFragment { includeMenuPrincipal() }
    }
    post(FragmentsRestritosEnum.HeaderDashboard.path) {
        val sessao = obterSessao()
        call.respondFragment { includeHeaderLogado (sessao) }
    }
}

enum class FragmentsRestritosEnum(
    override val path: String,
    override val method: FormMethod,
): IFragmentEnum {
    Menu("fragments/menu_principal", FormMethod.post),
    HeaderDashboard("fragments/header_dashboard", FormMethod.post)
}