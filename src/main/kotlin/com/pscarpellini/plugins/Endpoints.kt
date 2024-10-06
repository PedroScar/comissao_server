package com.pscarpellini.plugins

import com.pscarpellini.enums.ContaStatusEnum
import com.pscarpellini.enums.EndpointsEnum
import com.pscarpellini.enums.PagesEnum
import com.pscarpellini.model.loja.LojaRepository
import com.pscarpellini.model.funcionario.Funcionario
import com.pscarpellini.model.funcionario.FuncionarioRepository
import com.pscarpellini.model.login.LoginRepository
import com.pscarpellini.pages.addUserPage
import com.pscarpellini.pages.homePage
import com.pscarpellini.session.Sessao
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.html.respondHtml
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun Application.configureEndpoints() {

    val loginRepository: LoginRepository by inject()
    val funcionarioRepository: FuncionarioRepository by inject()
    val lojaRepository: LojaRepository by inject()

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
        post(EndpointsEnum.CriarUserRequest.path) {
            val params = call.receiveParameters()

            val nome = params["nome"]
            val endereco = params["endereco"]
            val cpf = params["cpf"]
            val email = params["email"]
            val telefone = params["telefone"]
            val username = params["username"]
            val password = params["password"]
            val status = ContaStatusEnum.getByName(params["status"].toString())

            if (nome == null ||
                cpf == null ||
                email == null ||
                telefone == null ||
                username == null ||
                password == null
            ) {
                call.respondHtml {
                    addUserPage(errorMessage = "Todos os campos são obrigatórios.")
                }
            } else {
                if (funcionarioRepository.adicionarUsuario(
                        Funcionario(
                            idLoja = Sessao.loja?.id ?: 0,
                            nome = nome,
                            endereco = endereco ?: "",
                            cpf = cpf,
                            email = email,
                            telefone = telefone,
                            status = status
                        )
                    )
                )
                    call.respondHtml {
                        homePage(successMessage = "Usuário adicionado com sucesso")
                    }
                else
                    call.respondHtml {
                        homePage(errorMessage = "Erro ao criar novo usuário")
                    }
            }
        }
    }
}
