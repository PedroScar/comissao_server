package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.backend.repositories.implementations.ColaboradorRepositoryPostgres
import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import com.pscarpellini.backend.repositories.implementations.ClienteRepositoryPostgres
import com.pscarpellini.backend.repositories.interfaces.ContasRepository
import com.pscarpellini.backend.repositories.implementations.ContasRepositoryPostgres
import com.pscarpellini.backend.repositories.interfaces.LoginRepository
import com.pscarpellini.backend.repositories.implementations.LoginRepositoryPostgres
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            single<ContasRepository> { ContasRepositoryPostgres(get()) }
            single<ColaboradorRepository> { ColaboradorRepositoryPostgres() }
            single<LoginRepository> { LoginRepositoryPostgres() }
            single<ClienteRepository> { ClienteRepositoryPostgres() }
        })
    }
}
