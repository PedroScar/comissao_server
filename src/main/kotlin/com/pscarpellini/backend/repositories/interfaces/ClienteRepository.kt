package com.pscarpellini.backend.repositories.interfaces

import com.pscarpellini.backend.models.dto.requests.ClienteRequest
import com.pscarpellini.backend.models.vos.ClienteVO

interface ClienteRepository {
    suspend fun obterClientes(): List<ClienteVO>
    suspend fun adicionarCliente(cliente: ClienteRequest): Boolean
    suspend fun atualizarCliente(cliente: ClienteVO): Boolean
    suspend fun desativarCliente(cliente: ClienteVO): Boolean
}