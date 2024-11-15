package domain

import Sessao
import enums.ContaStatusEnum
import enums.ContaTipoEnum
import enums.EndpointsEnum
import io.ktor.server.application.Application
import io.ktor.server.html.respondHtml
import io.ktor.server.request.receiveParameters
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import model.colaborador.Colaborador
import model.funcionario.Funcionario
import org.koin.ktor.ext.inject
import pages.comissaoAddUserPage
import pages.comissaoHomePage
import kotlin.getValue

fun Application.comissaoEndpoints() {

    val colaboradorRepository: model.colaborador.ColaboradorRepository by inject()
    val funcionarioRepository: model.funcionario.FuncionarioRepository by inject()

    routing {
        post(EndpointsEnum.CriarUserRequest.path) {
            val params = call.receiveParameters()

            val nome = params["nome"]
            val endereco = params["endereco"]
            val cpf = params["cpf"]
            val email = params["email"]
            val telefone = params["telefone"]
            val username = params["username"]
            val password = params["password"]
            val status = ContaStatusEnum.Companion.getStatus(params["status"].toString())
            val tipo = ContaTipoEnum.Companion.getTipo(params["tipo"].toString())

            if (nome == null ||
                cpf == null ||
                email == null ||
                telefone == null ||
                username == null ||
                password == null
            ) {
                call.respondHtml {
                    comissaoAddUserPage(errorMessage = "Todos os campos são obrigatórios.")
                }
            } else {

                runCatching {
                    if (tipo == ContaTipoEnum.FUNCIONARIO) {
                        val novoUsuario = Funcionario(
                           idloja = Sessao.idPai ?: 0,
                            nome = nome,
                            endereco = endereco ?: "",
                            doc = cpf,
                            email = email,
                            telefone = telefone,
                            status = status.id
                        )
                        funcionarioRepository.adicionarUsuario(novoUsuario)
                    //    loginRepository.criarLogin(username, password, ContaTipoEnum.FUNCIONARIO.id)
                    } else {
                        val novoUsuario = Colaborador(
                            idloja = Sessao.idPai ?: 0,
                            nome = nome,
                            endereco = endereco ?: "",
                            doc = cpf,
                            email = email,
                            telefone = telefone,
                            status = status.id
                        )
                        colaboradorRepository.adicionarUsuario(novoUsuario)
                    //    loginRepository.criarLogin(username, password, ContaTipoEnum.COLABORADOR.id)
                    }
                }.onFailure {
                    call.respondHtml { comissaoHomePage(errorMessage = "Erro ao criar novo usuário") }
                }.onSuccess {
                    call.respondHtml { comissaoHomePage(successMessage = "Usuário adicionado com sucesso") }
                }
            }
        }

    }
}
