package com.pscarpellini

import com.pscarpellini.frontend.tailwind.TailwindConfigsGenerator
import com.pscarpellini.plugins.*
import com.pscarpellini.rotas.configureStatics
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    TailwindConfigsGenerator().generate()
    EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    configureDI()
    configureDatabases()
    configurePages()
    configureEndpoints()
    configureSessions()
    configureStatics()
}
