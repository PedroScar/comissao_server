package com.pscarpellini.backend.repositories.implementations

import com.pscarpellini.backend.database.tables.ContasTable
import com.pscarpellini.backend.models.vos.ContaVO
import com.pscarpellini.backend.repositories.interfaces.ContasRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ContasRepositoryPostgres(
    private val db: Database
) : ContasRepository {

    override suspend fun obterContas(): List<String> {
        return transaction(db) {
            ContasTable.selectAll().map { row ->
                val nome = row[ContasTable.nome]
                println("Nome: $nome")
                nome
            }
        }
    }

//    override suspend fun adicionarConta(conta: Conta) {
//        return transaction(db) {
//            ContasTable.insert {
//                it[this.tipoConta] = conta.tipoConta
//                it[this.nome] = conta.nome
//                it[this.cpf] = conta.cpf
//                it[this.endereco] = conta.endereco
//                it[this.email] = conta.email
//                it[this.telefone] = conta.telefone
//                it[this.status] = conta.status
//                it[this.usuario] = conta.usuario
//                it[this.senha] = conta.senha
//            }
//        }
//    }

    override suspend fun adicionarConta(contaVO: ContaVO): Unit = suspendTransaction {
//        runCatching {
//            ContaDAO.new {
//                clienteId = conta.clienteId
//                tipoConta = conta.tipoConta
//                nome = conta.nome
//                cpf = conta.cpf
//                endereco = conta.endereco
//                email = conta.email
//                telefone = conta.telefone
//                status = conta.status
//                usuario = conta.usuario
//                senha = conta.senha
//            }
//            true
//        }.getOrElse { false }
    }
}