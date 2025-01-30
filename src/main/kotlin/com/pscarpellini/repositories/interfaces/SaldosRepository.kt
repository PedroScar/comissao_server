package com.pscarpellini.repositories.interfaces

import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.SaldoVO

interface SaldosRepository {
    suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO>
    suspend fun carregarSaldoContas(): DbResponse<SaldoVO>
    suspend fun modificarSaldo(contaId: Int, novoValor: Double): DbResponse<SaldoVO>
}