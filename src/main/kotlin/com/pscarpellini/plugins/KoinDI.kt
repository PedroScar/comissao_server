package com.pscarpellini.plugins

import com.pscarpellini.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.repositories.implementations.ColaboradorRepositoryPostgres
import com.pscarpellini.repositories.interfaces.LojaRepository
import com.pscarpellini.repositories.implementations.LojaRepositoryPostgres
import com.pscarpellini.repositories.interfaces.FuncionarioRepository
import com.pscarpellini.repositories.implementations.FuncionarioRepositoryPostgres
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
            single<FuncionarioRepository> { FuncionarioRepositoryPostgres() }
            single<ColaboradorRepository> { ColaboradorRepositoryPostgres() }
            single<LoginRepository> { LoginRepositoryPostgres() }
            single<LojaRepository> { LojaRepositoryPostgres() }
        })
    }
}
