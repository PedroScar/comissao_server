package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.ExtratoDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.daos.SaldoDAO
import com.pscarpellini.database.tables.ClientesTable
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.utils.saldoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SaldoVO
import com.pscarpellini.repositories.interfaces.SaldosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import java.time.LocalDateTime
import kotlin.Double
import kotlin.Int

class SaldosRepositoryPostgres : SaldosRepository {

    override suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO> = suspendTransaction {
        val saldo = SaldoDAO
            .find { SaldosTable.contaId eq contaId }
            .limit(1)
            .map(::saldoDaoToModel)
            .firstOrNull()
        if (saldo != null) DbResponse.Successo(saldo) else DbResponse.Erro(
            data = null,
            message = "Ocorreu um erro ao recuperar o saldo"
        )
    }

    override suspend fun carregarSaldoContas(nome: String, clienteId: Int): DbResponse<List<SaldoVO>> = suspendTransaction {
        val saldos = SaldosTable
            .join(ContasTable, JoinType.INNER, additionalConstraint = { SaldosTable.contaId eq ContasTable.id })
            .join(ClientesTable, JoinType.INNER, additionalConstraint = { ContasTable.clienteId eq ClientesTable.id })
            .selectAll()
            .where {
                (ContasTable.clienteId eq clienteId)
                    .and(
                        (ContasTable.nome.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.usuario.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.email.lowerCase().like("%${nome.lowercase()}%"))
                    )
            }
            .map(::saldoDaoToModel)

        DbResponse.Successo(saldos)
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
                    conta = contaDaoToModel(dao = contaDonoIdDao),
//                    contaId = contaDonoId,
                    saldo = saldoDao.saldo,
                )
            )
        }
}