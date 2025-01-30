package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.ExtratoDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.daos.SaldoDAO
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.database.utils.saldoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.SaldoVO
import com.pscarpellini.repositories.interfaces.SaldosRepository
import com.pscarpellini.suspendTransaction
import java.time.LocalDateTime
import kotlin.Double
import kotlin.Int

class SaldosRepositoryPostgres : SaldosRepository {

    override suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO> = suspendTransaction {
        val saldo = SaldoDAO
            .find { (SaldosTable.contaId eq contaId) }
            .limit(1)
            .map(::saldoDaoToModel)
            .firstOrNull()
        if (saldo != null) DbResponse.Successo(saldo) else DbResponse.Erro(
            data = null,
            message = "Ocorreu um erro ao recuperar o saldo"
        )
    }

    override suspend fun carregarSaldoContas(): DbResponse<SaldoVO> = suspendTransaction {
        DbResponse.Erro(message = "NÃO IMPLEMENTADO AINDA")
    }

    override suspend fun modificarSaldo(
        contaCriacaoId: Int,
        contaDonoId: Int,
        promocaoId: Int,
        valor: Double,
        isCredito: Boolean
    ): DbResponse<SaldoVO> =
        suspendTransaction {
            val saldoDao = SaldoDAO.find { SaldosTable.contaId eq contaDonoId }.firstOrNull()
            val contaCriacaoIdDao = ContaDAO.findById(contaCriacaoId)
                ?: throw IllegalArgumentException("Conta $contaCriacaoId não encontrada")
            val contaDonoIdDao = ContaDAO.findById(contaDonoId)
                ?: throw IllegalArgumentException("Conta $contaDonoId não encontrada")
            val promocaoId = PromocaoDAO.findById(promocaoId)
                ?: throw IllegalArgumentException("Promocao $promocaoId não encontrada")

            if (saldoDao == null) return@suspendTransaction DbResponse.Erro(message = "Saldo da conta não encontrado.")

            runCatching {
                saldoDao.apply {
                    // Marca a promoção como encerrada
                    saldo = saldoDao.saldo + if (isCredito) valor else -valor
                }
            }.onFailure {
                it.printStackTrace()
                return@suspendTransaction DbResponse.Erro(message = "Erro ao modificar saldo")
            }

            runCatching {
                ExtratoDAO.new {
                    this.contaCriacaoId = contaCriacaoIdDao.id
                    this.contaDonoId = contaDonoIdDao.id
                    this.promocaoId = promocaoId.id
                    this.dataCriacao = LocalDateTime.now()
                    this.valor = valor
                    this.isCredito = isCredito
                }
            }.onFailure {
                it.printStackTrace()
                return@suspendTransaction DbResponse.Erro(message = "Erro ao modificar saldo")
            }

            DbResponse.Successo(
                SaldoVO(
                    contaId = contaDonoId,
                    saldo = saldoDao.saldo,
                )
            )
        }
}