package com.pscarpellini

import com.pscarpellini.backend.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureDatabases()
    configurePages()
    configureEndpoints()
    configureSessions()
}
