package com.pscarpellini

import com.pscarpellini.backend.plugins.*
import com.pscarpellini.frontend.plugins.configurePages
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureDatabases()
    configurePages()
    configureEndpoints()
}
