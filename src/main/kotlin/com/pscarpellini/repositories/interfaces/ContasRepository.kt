package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO

interface ContasRepository {
    suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO>
    suspend fun carregarUsuarios(clienteId: Int): DbResponse<List<ContaVO>>
    suspend fun criarUsuario(conta: ContaVO): DbResponse<ContaVO>
}