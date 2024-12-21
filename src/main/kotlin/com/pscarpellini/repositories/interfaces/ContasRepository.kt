package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.vos.ContaVO

interface ContasRepository {
    suspend fun obterContas(): List<String>
    suspend fun adicionarConta(contaVO: ContaVO)
}