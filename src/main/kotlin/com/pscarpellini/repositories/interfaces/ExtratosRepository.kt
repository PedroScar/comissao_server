package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ExtratoVO
import com.pscarpellini.models.vos.SaldoVO

interface ExtratosRepository {
    suspend fun carregarExtratos(nome: String, clienteId: Int): DbResponse<List<ExtratoVO>>
    suspend fun carregarExtratosRecentes(clienteId: Int): DbResponse<List<ExtratoVO>>
}