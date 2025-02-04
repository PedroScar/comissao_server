package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.SaldoVO

interface SaldosRepository {
    suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO>
    suspend fun carregarSaldoContas(nome: String, clienteId: Int): DbResponse<List<SaldoVO>>
    suspend fun modificarSaldo(
        contaCriacaoId: Int,
        contaDonoId: Int,
        promocaoId: Int,
        valor: Double,
        isCredito: Boolean
    ): DbResponse<SaldoVO>
}