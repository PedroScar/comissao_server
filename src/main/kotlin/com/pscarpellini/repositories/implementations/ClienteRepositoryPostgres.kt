package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.utils.clienteDaoToModel
import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.tables.ClientesTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.suspendTransaction
import java.time.LocalDateTime

class ClienteRepositoryPostgres : ClienteRepository {

    override suspend fun obterClientes() = suspendTransaction {
        ClienteDAO.all().map(::clienteDaoToModel)
    }

    override suspend fun carregarCliente(clienteId: Int): DbResponse<ClienteVO> = suspendTransaction {
        val cliente = runCatching {
            ClienteDAO
                .find { (ClientesTable.id eq clienteId) }
                .firstOrNull()
                ?.let(::clienteDaoToModel)
        }.onFailure { it.printStackTrace() }.getOrThrow()

        if (cliente != null) DbResponse.Successo(cliente)
        else DbResponse.Erro(message = "Cliente não encontrado.")

    }

    override suspend fun adicionarCliente(cliente: ClienteRequest): Boolean = suspendTransaction {
        runCatching {
            val teste = ClienteDAO.new {
                nome = cliente.nome
                endereco = cliente.endereco
                cnpj = cliente.cnpj
                email = cliente.email
                telefone = cliente.telefone
                status = cliente.status
                dataCriacao = LocalDateTime.now()
            }
            true
        }.onFailure {
        }.getOrDefault(false)
    }

    override suspend fun editarCliente(cliente: ClienteVO): DbResponse<ClienteVO> = suspendTransaction {
        val clienteDb = ClienteDAO.findById(cliente.id ?: -4)
            ?: throw IllegalArgumentException("Cliente com ID ${cliente.id} não encontrado")

        runCatching {
            clienteDb.apply {
                nome = cliente.nome
                endereco = cliente.endereco
                cnpj = cliente.cnpj
                email = cliente.email
                telefone = cliente.telefone
                status = cliente.status
                logo = cliente.logo
            }
            DbResponse.Successo(clienteDaoToModel(clienteDb))
        }.onFailure {
            println("==================")
            println("ERRO DB: ${it.message}")
            println("==================")
            DbResponse.Erro(null, message = it.message.toString())
        }.getOrDefault(DbResponse.Erro(null, message = "Ops... algo de errado aconteceu!"))
    }

    override suspend fun desativarCliente(clienteId: Int): DbResponse<ClienteVO> {
        val clienteDesativado = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID ${clienteId} não encontrado")
        clienteDesativado.status = "DESATIVADO"
        val cliente = clienteDaoToModel(clienteDesativado)
        return editarCliente(cliente)
    }
}

