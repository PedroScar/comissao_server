package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.requests.ClienteRequest
import com.pscarpellini.models.vos.ClienteVO

interface ClienteRepository {
    suspend fun obterClientes(): List<ClienteVO>
    suspend fun carregarCliente(clienteId: Int): DbResponse<ClienteVO>
    suspend fun adicionarCliente(cliente: ClienteRequest): Boolean
    suspend fun editarCliente(cliente: ClienteVO): DbResponse<ClienteVO>
    suspend fun desativarCliente(clienteId: Int): DbResponse<ClienteVO>
}