package com.pscarpellini.backend.plugins

import com.pscarpellini.backend.enums.EndpointsEnum
import com.pscarpellini.backend.repositories.interfaces.ColaboradorRepository
import com.pscarpellini.backend.repositories.interfaces.FuncionarioRepository
import com.pscarpellini.backend.repositories.interfaces.LoginRepository
import com.pscarpellini.backend.repositories.interfaces.LojaRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.receiveParameters
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.configureEndpoints() {

    val colaboradorRepository: ColaboradorRepository by inject()
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

//            if (loginRepository.validarLogin(username.toString(), password.toString()))
//                call.respondRedirect(PagesEnum.Home.path)
//            else {
//                val encodedErrorMessage = URLEncoder.encode(
//                    "Credenciais inválidas, tente novamente.",
//                    StandardCharsets.UTF_8.toString()
//                )
//                call.respondRedirect("/?error=$encodedErrorMessage")
//            }
        }
//        post(EndpointsEnum.CriarUserRequest.path) {
//            val params = call.receiveParameters()
//
//            val nome = params["nome"]
//            val endereco = params["endereco"]
//            val cpf = params["cpf"]
//            val email = params["email"]
//            val telefone = params["telefone"]
//            val username = params["username"]
//            val password = params["password"]
//            val status = ContaStatusEnum.getStatus(params["status"].toString())
//            val tipo = ContaTipoEnum.getTipo(params["tipo"].toString())
//
//            if (nome == null ||
//                cpf == null ||
//                email == null ||
//                telefone == null ||
//                username == null ||
//                password == null
//            ) {
//                call.respondHtml {
//                    addUserPage(errorMessage = "Todos os campos são obrigatórios.")
//                }
//            } else {
//
//                runCatching {
//                    if (tipo == ContaTipoEnum.FUNCIONARIO) {
//                        val novoUsuario = Funcionario(
//                            idloja = Sessao.idPai ?: 0,
//                            nome = nome,
//                            endereco = endereco ?: "",
//                            doc = cpf,
//                            email = email,
//                            telefone = telefone,
//                            status = status.id
//                        )
//                        funcionarioRepository.adicionarUsuario(novoUsuario)
//                        loginRepository.criarLogin(username, password, ContaTipoEnum.FUNCIONARIO.id)
//                    } else {
//                        val novoUsuario = Colaborador(
//                            idloja = Sessao.idPai ?: 0,
//                            nome = nome,
//                            endereco = endereco ?: "",
//                            doc = cpf,
//                            email = email,
//                            telefone = telefone,
//                            status = status.id
//                        )
//                        colaboradorRepository.adicionarUsuario(novoUsuario)
//                        loginRepository.criarLogin(username, password, ContaTipoEnum.COLABORADOR.id)
//                    }
//                }.onFailure {
//                    call.respondHtml { homePage(errorMessage = "Erro ao criar novo usuário") }
//                }.onSuccess {
//                    call.respondHtml { homePage(successMessage = "Usuário adicionado com sucesso") }
//                }
//            }
//        }
    }
}
