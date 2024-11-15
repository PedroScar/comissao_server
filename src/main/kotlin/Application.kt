package model

import configureDatabases
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import model.plugins.configureDI
import model.plugins.configureEndpoints
import model.plugins.configurePages

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureDI()
    configureDatabases()
    configurePages()
    configureEndpoints()
}
