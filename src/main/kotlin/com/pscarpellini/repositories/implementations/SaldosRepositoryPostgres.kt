package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.daos.SaldoDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.database.utils.saldoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.SaldoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.SaldosRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.or
import java.time.LocalDateTime

class SaldosRepositoryPostgres : SaldosRepository {

    override suspend fun carregarSaldoConta(contaId: Int): DbResponse<SaldoVO> = suspendTransaction {
        val saldo = SaldoDAO
            .find { (SaldosTable.contaId eq contaId) }
            .limit(1)
            .map(::saldoDaoToModel)
            .firstOrNull()
        if(saldo != null) DbResponse.Successo(saldo) else DbResponse.Erro(data = null, message = "Ocorreu um erro ao recuperar o saldo")
    }

    override suspend fun carregarSaldoContas(): DbResponse<SaldoVO> = suspendTransaction {
        DbResponse.Erro(message = "NÃO IMPLEMENTADO AINDA")
    }

    override suspend fun modificarSaldo(contaId: Int, novoValor: Double): DbResponse<SaldoVO> = suspendTransaction {
        DbResponse.Erro(message = "NÃO IMPLEMENTADO AINDA")
    }
}