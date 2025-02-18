package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.tableModels.SaldoDB
import com.pscarpellini.models.vos.SaldoVO

interface SaldosRepository {
    suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO>
    suspend fun carregarSaldoContaAPI(contaId: Int): DbResponse<SaldoDB>
    suspend fun carregarSaldoContas(nome: String, clienteId: Int): DbResponse<List<SaldoVO>>
    suspend fun modificarSaldo(
        contaResponsavelId: Int,
        contaSaldoId: Int,
        promocaoId: Int? = null,
        valor: Double,
        isCredito: Boolean
    ): DbResponse<SaldoVO>
}