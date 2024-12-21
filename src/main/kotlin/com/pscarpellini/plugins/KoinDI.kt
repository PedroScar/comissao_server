package com.pscarpellini.plugins

import com.pscarpellini.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.repositories.implementations.ColaboradorRepositoryPostgres
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.repositories.implementations.ClienteRepositoryPostgres
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.implementations.ContasRepositoryPostgres
import com.pscarpellini.repositories.interfaces.LoginRepository
import com.pscarpellini.repositories.implementations.LoginRepositoryPostgres
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
            single<LoginRepository> { LoginRepositoryPostgres() }
            single<ClienteRepository> { ClienteRepositoryPostgres() }
        })
    }
}
