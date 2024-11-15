package model.plugins

import di.ComissaoDI
import io.ktor.server.application.Application
import io.ktor.server.application.install
import model.login.di.LoginDI
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    install(Koin) {
        slf4jLogger()
        modules(module {
            ComissaoDI()
            LoginDI()
        })
    }
}
