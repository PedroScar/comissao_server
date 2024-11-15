package model.login.di

import model.login.models.LoginRepository
import model.login.models.LoginRepositoryPostgres
import org.koin.dsl.module

fun LoginDI() = module {
    single<LoginRepository> { LoginRepositoryPostgres() }
}