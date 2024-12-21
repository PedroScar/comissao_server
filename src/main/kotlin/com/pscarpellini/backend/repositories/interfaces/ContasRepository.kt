package com.pscarpellini.backend.repositories.interfaces

import com.pscarpellini.backend.models.vos.ContaVO

interface ContasRepository {
    suspend fun obterContas(): List<String>
    suspend fun adicionarConta(contaVO: ContaVO)
}