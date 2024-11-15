package model.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import model.login.endpoints.configureEndpointsLogin

fun Application.configureEndpoints() {
    install(ContentNegotiation) {
        json()
    }

    configureEndpointsLogin()
}
