package com.pscarpellini.plugins

import com.pscarpellini.tools.email.EmailSender
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
            single<EmailSender> {
                EmailSender(
                    host = "smtp.zoho.com",
                    port = 587,
                    username = "suporte@lumenapps.com.br",
                    password = "eNAb9LCF95F2JTUGDUby7xrWEIUWin0q",
                    fromEmail = "Lumen Apps",
                )
            }
        })
    }
}