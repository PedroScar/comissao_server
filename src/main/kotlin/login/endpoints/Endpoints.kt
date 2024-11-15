package model.login.endpoints

import enums.EndpointsEnum
import enums.PagesEnum
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import model.login.models.LoginRepository
import org.koin.ktor.ext.inject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun Application.configureEndpointsLogin() {

    val loginRepository: LoginRepository by inject()

    install(ContentNegotiation) {
        json()
    }

    routing {
        post(EndpointsEnum.LoginRequest.path) {
            val parameters = call.receiveParameters()
            val username = parameters["username"]
            val password = parameters["password"]

            if (loginRepository.validarLogin(username.toString(), password.toString()))
                call.respondRedirect(PagesEnum.Home.path)
            else {
                val encodedErrorMessage = URLEncoder.encode(
                    "Credenciais inválidas, tente novamente.",
                    StandardCharsets.UTF_8.toString()
                )
                call.respondRedirect("/?error=$encodedErrorMessage")
            }
        }
    }
}
