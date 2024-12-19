package com.pscarpellini.frontend.routes.restritos.fragments

import com.pscarpellini.frontend.extensions.respondFragment
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import io.ktor.server.routing.*

fun Routing.fragmentsRestritos() {
    post(FragmentsRestritosEnum.Menu.path) {
        call.respondFragment { includeMenuPrincipal() }
    }
    post(FragmentsRestritosEnum.Menu.path) {
        call.respondFragment { includeMenuPrincipal() }
    }
}