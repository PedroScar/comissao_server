package com.pscarpellini.rotas

import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.gerarSenhaBasica
import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.models.responses.ClienteComContaResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

fun Route.endpointsAbertos(
    clienteRepository: ClienteRepository,
    contasRepository: ContasRepository
) {

    post("/api/criar-cliente") {
        runCatching {
            // Validar header "SGR-LUMEN-TK"
            val headerToken = call.request.headers["SGR-LUMEN-TK"]
            val tokenEsperado = System.getenv("SGR_LUMEN_TK")
                ?: throw IllegalArgumentException("Variável de ambiente SGR_LUMEN_TK não configurada")

            if (headerToken != tokenEsperado) {
                call.respond(
                    HttpStatusCode.Unauthorized,
                    mapOf("erro" to "Token de autorização inválido")
                )
                return@post
            }

            // Receber dados do cliente
            val clienteRequest = call.receive<ClienteRequest>()

            // Criar cliente
            val clienteCriado = clienteRepository.adicionarCliente(clienteRequest)
            if (!clienteCriado) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("erro" to "Erro ao criar cliente")
                )
                return@post
            }

            // Obter o cliente recém-criado
            val clientes = clienteRepository.obterClientes()
            val novoCliente = clientes.find { it.cnpj == clienteRequest.cnpj }
                ?: throw IllegalArgumentException("Erro ao recuperar cliente criado")

            // Gerar usuario e senha para a conta administradora
            var nomeUsuario = criarNomeDeUsuario(clienteRequest.nome)
            var senhaGerada = gerarSenhaBasica()
            var tentativas = 0
            val maxTentativas = 3

            // Tentar criar a conta com retry
            var contaCriada = false
            while (tentativas < maxTentativas && !contaCriada) {
                try {
                    val contaAdministrador = ContaVO(
                        cliente = novoCliente,
                        nome = clienteRequest.nome,
                        endereco = clienteRequest.endereco,
                        cpf = "000.000.000-00", // CPF padrão para conta administradora
                        email = clienteRequest.email,
                        telefone = clienteRequest.telefone,
                        usuario = nomeUsuario,
                        senha = senhaGerada,
                        status = "ATIVO",
                        tipoConta = PerfisDeAcessoEnum.ADMINISTRADOR.slug,
                        imagemDePerfil = null
                    )

                    // Criar a conta
                    contasRepository.criarUsuario(contaAdministrador)
                    contaCriada = true

                } catch (e: Exception) {
                    tentativas++
                    if (tentativas < maxTentativas) {
                        // Tentar com parâmetros diferentes
                        nomeUsuario = criarNomeDeUsuario(clienteRequest.nome) + Random.nextInt(100, 999)
                        senhaGerada = gerarSenhaBasica()
                        println("Tentativa ${tentativas} de criar conta falhou. Tentando novamente com novo usuário...")
                    }
                }
            }

            // Retornar resposta com usuario e senha
            val resposta = ClienteComContaResponse(
                clienteId = novoCliente.id ?: 0,
                clienteNome = novoCliente.nome,
                usuarioAdministrador = nomeUsuario,
                senhaAdministrador = senhaGerada,
                emailAdministrador = clienteRequest.email,
                mensagem = if (contaCriada) {
                    "Cliente e conta administradora criados com sucesso!"
                } else {
                    "Cliente criado com sucesso! Aviso: Não foi possível criar a conta administradora automaticamente. Tente novamente com os dados acima."
                }
            )

            call.respond(HttpStatusCode.Created, resposta)
        }.onFailure {
            println("=============================================================================")
            println("ERRO AO CRIAR CLIENTE: ${it.message}")
            println(it.stackTraceToString())
            println("=============================================================================")
            call.respond(
                HttpStatusCode.BadRequest,
                mapOf("erro" to (it.message ?: "Erro ao processar requisição"))
            )
        }
    }

}