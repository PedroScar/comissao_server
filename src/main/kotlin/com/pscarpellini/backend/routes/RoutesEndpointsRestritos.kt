package com.pscarpellini.backend.routes

import com.pscarpellini.backend.enums.ContaStatusEnum
import com.pscarpellini.backend.enums.ContaTipoEnum
import com.pscarpellini.backend.enums.endpoints.EndpointsLogadosEnum
import com.pscarpellini.frontend.pages.restritos.addUserPage
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.endpointsRestritos() {

    routing {
        post(EndpointsLogadosEnum.CriarUserRequest.path) {
            val params = call.receiveParameters()

            val nome = params["nome"]
            val endereco = params["endereco"]
            val cpf = params["cpf"]
            val email = params["email"]
            val telefone = params["telefone"]
            val username = params["username"]
            val password = params["password"]
            val status = ContaStatusEnum.getStatus(params["status"].toString())
            val tipo = ContaTipoEnum.getTipo(params["tipo"].toString())

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
            }
        }
    }
}