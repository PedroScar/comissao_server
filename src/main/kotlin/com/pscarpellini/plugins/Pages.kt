package com.pscarpellini.plugins

import com.pscarpellini.exceptions.NaoLogadoException
import com.pscarpellini.frontend.pages.geral.not_found.notFoundPage
import com.pscarpellini.frontend.style.styledRouting
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.rotas.fragmentsRestritos
import com.pscarpellini.rotas.paginasAbertas
import com.pscarpellini.rotas.paginasRestritas
import com.pscarpellini.rotas.widgetsInicio
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configurePages() {

    val clientesRepository: ClienteRepository by inject()
    val contasRepository: ContasRepository by inject()
    val promocoesRepository: PromocoesRepository by inject()

    install(StatusPages) {
        exception<NaoLogadoException> { call, cause ->
            call.respondText(text = "Você não poderia estar aqui!", status = HttpStatusCode.Forbidden)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondHtml(HttpStatusCode.NotFound) { notFoundPage() }
        }
        status(HttpStatusCode.OK) { _, _ -> }
    }

    styledRouting {
        staticResources("/static", "static")

        paginasAbertas(contasRepository)
        paginasRestritas(contasRepository, promocoesRepository)

        widgetsInicio(promocoesRepository)

        fragmentsRestritos(contasRepository, promocoesRepository)
    }
}
