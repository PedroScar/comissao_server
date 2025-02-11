package com.pscarpellini.plugins

import com.pscarpellini.repositories.implementations.*
import com.pscarpellini.repositories.interfaces.*
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<ContasRepository> { ContasRepositoryPostgres() }
            single<ColaboradorRepository> { ColaboradorRepositoryPostgres() }
            single<ContadoresDashboardViewRepository> { ContadoresDashboardViewRepositoryPostgres() }
            single<ClienteRepository> { ClienteRepositoryPostgres() }
            single<PromocoesRepository> { PromocoesRepositoryPostgres() }
            single<SaldosRepository> { SaldosRepositoryPostgres() }
            single<ExtratosRepository> { ExtratosRepositoryPostgres() }
            single<VideosRepository> { VideosRepositoryPostgres() }
        })
    }
}