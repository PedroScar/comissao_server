package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.clienteDaoToModel
import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.repositories.interfaces.ClienteRepository
import com.pscarpellini.suspendTransaction
import java.time.LocalDateTime

class ClienteRepositoryPostgres : ClienteRepository {

    override suspend fun obterClientes() = suspendTransaction {
        ClienteDAO.all().map(::clienteDaoToModel)
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

    override suspend fun atualizarCliente(cliente: ClienteVO): Boolean = suspendTransaction {
        runCatching {
            ClienteDAO.findByIdAndUpdate(cliente.id) {
            }
            true
        }.getOrDefault(false)
    }

    override suspend fun desativarCliente(cliente: ClienteVO): Boolean = suspendTransaction {
        runCatching {
            ClienteDAO.findByIdAndUpdate(cliente.id) {
            }
            true
        }.getOrDefault(false)
    }
}

