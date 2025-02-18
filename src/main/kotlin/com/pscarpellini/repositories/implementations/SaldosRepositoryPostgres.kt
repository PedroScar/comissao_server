package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.ExtratoDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.daos.SaldoDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.utils.saldoRowToDB
import com.pscarpellini.database.utils.saldoRowToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.tableModels.SaldoDB
import com.pscarpellini.models.vos.SaldoVO
import com.pscarpellini.repositories.interfaces.SaldosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime
import kotlin.Double
import kotlin.Int

class SaldosRepositoryPostgres : SaldosRepository {

    override suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO> = suspendTransaction {
        val saldo = SaldoDAO
            .find { SaldosTable.contaId eq contaId }
            .limit(1)
            .map(::saldoRowToModel)
            .firstOrNull()
        if (saldo != null) DbResponse.Successo(saldo) else DbResponse.Erro(
            data = null,
            message = "Ocorreu um erro ao recuperar o saldo"
        )
    }

    override suspend fun carregarSaldoContaAPI(contaId: Int): DbResponse<SaldoDB> = suspendTransaction {
        val saldo = SaldoDAO
            .find { SaldosTable.contaId eq contaId }
            .limit(1)
            .map(::saldoRowToDB)
            .firstOrNull()
        if (saldo != null) DbResponse.Successo(saldo) else DbResponse.Erro(
            data = null,
            message = "Ocorreu um erro ao recuperar o saldo"
        )
    }

    override suspend fun carregarSaldoContas(nome: String, clienteId: Int): DbResponse<List<SaldoVO>> = suspendTransaction {
        val saldos = SaldosTable
            .join(ContasTable, JoinType.INNER, additionalConstraint = { SaldosTable.contaId eq ContasTable.id })
            .selectAll()
            .where {
                (ContasTable.clienteId eq clienteId)
                    .and(
                        (ContasTable.nome.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.usuario.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.email.lowerCase().like("%${nome.lowercase()}%"))
                    )
            }
            .map(::saldoRowToModel)
        DbResponse.Successo(saldos)
    }

    override suspend fun modificarSaldo(
        contaResponsavelId: Int,
        contaSaldoId: Int,
        promocaoId: Int?,
        valor: Double,
        isCredito: Boolean
    ): DbResponse<SaldoVO> = suspendTransaction {
        val saldoDao = SaldoDAO.find { SaldosTable.contaId eq contaSaldoId }.firstOrNull()
        val contaResponsavelIdDao = ContaDAO.findById(contaResponsavelId)
            ?: throw IllegalArgumentException("Conta $contaResponsavelId não encontrada")
        val contaSaldoIdDao = ContaDAO.findById(contaSaldoId)
            ?: throw IllegalArgumentException("Conta $contaSaldoId não encontrada")
        val promocaoIdDao = promocaoId?.let { PromocaoDAO.findById(promocaoId) }

        if (saldoDao == null) return@suspendTransaction DbResponse.Erro(message = "Saldo da conta não encontrado.")

        runCatching {
            ExtratoDAO.new {
                this.contaResponsavelId = contaResponsavelIdDao.id
                this.contaSaldoId = contaSaldoIdDao.id
                this.promocaoId = promocaoIdDao?.id
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
                conta = contaDaoToModel(dao = contaSaldoIdDao),
                saldo = saldoDao.saldo,
            )
        )
    }
}