package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO

interface ContasRepository {
    suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO>
    suspend fun carregarUsuarios(nome: String = "", clienteId: Int): DbResponse<List<ContaVO>>
    suspend fun criarUsuario(conta: ContaVO): DbResponse<ContaVO>
}