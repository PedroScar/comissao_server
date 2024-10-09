package com.pscarpellini

import com.pscarpellini.plugins.*
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
