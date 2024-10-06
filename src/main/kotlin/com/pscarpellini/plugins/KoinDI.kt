package com.pscarpellini.plugins

import com.pscarpellini.model.colaborador.ColaboradorRepository
import com.pscarpellini.model.colaborador.ColaboradorRepositoryPostgres
import com.pscarpellini.model.loja.LojaRepository
import com.pscarpellini.model.loja.LojaRepositoryPostgres
import com.pscarpellini.model.funcionario.FuncionarioRepository
import com.pscarpellini.model.funcionario.FuncionarioRepositoryPostgres
import com.pscarpellini.model.login.LoginRepository
import com.pscarpellini.model.login.LoginRepositoryPostgres
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
