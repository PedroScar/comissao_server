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
                val host = environment.config.property("email.host").getString()
                val port = environment.config.property("email.port").getString().toInt()
                val username = environment.config.property("email.username").getString()
                val password = environment.config.property("email.password").getString()
                val fromEmail = environment.config.property("email.fromEmail").getString()

                EmailSender(
                    host = host,
                    port = port,
                    username = username,
                    password = password,
                    fromEmail = fromEmail,
                )
            }
        })
    }
}