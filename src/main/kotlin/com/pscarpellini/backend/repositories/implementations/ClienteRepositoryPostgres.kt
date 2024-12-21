package com.pscarpellini.backend.repositories.implementations

import com.pscarpellini.backend.database.clienteDaoToModel
import com.pscarpellini.backend.database.daos.ClienteDAO
import com.pscarpellini.backend.database.tables.ClientesTable
import com.pscarpellini.backend.models.dto.requests.ClienteRequest
import com.pscarpellini.backend.models.vos.ClienteVO
import com.pscarpellini.backend.repositories.interfaces.ClienteRepository
import com.pscarpellini.suspendTransaction

class ClienteRepositoryPostgres : ClienteRepository {

    override suspend fun obterClientes() = suspendTransaction {
        ClienteDAO.all().map(::clienteDaoToModel)
    }

    override suspend fun adicionarCliente(cliente: ClienteRequest): Boolean = runCatching {
        ClienteDAO.new {
            nome = cliente.nome
            endereco = cliente.endereco
            cnpj = cliente.cnpj
            email = cliente.email
            telefone = cliente.telefone
            status = cliente.status
        }
        true
    }.getOrDefault(false)

    override suspend fun atualizarCliente(cliente: ClienteVO): Boolean = runCatching {
        ClienteDAO.findByIdAndUpdate(cliente.id) {
        }
        true
    }.getOrDefault(false)

    override suspend fun desativarCliente(cliente: ClienteVO): Boolean = runCatching {
        ClienteDAO.findByIdAndUpdate(cliente.id) {
        }
        true
    }.getOrDefault(false)
}

