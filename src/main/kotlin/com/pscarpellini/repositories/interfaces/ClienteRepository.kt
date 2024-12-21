package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.models.vos.ClienteVO

interface ClienteRepository {
    suspend fun obterClientes(): List<ClienteVO>
    suspend fun adicionarCliente(cliente: ClienteRequest): Boolean
    suspend fun atualizarCliente(cliente: ClienteVO): Boolean
    suspend fun desativarCliente(cliente: ClienteVO): Boolean
}