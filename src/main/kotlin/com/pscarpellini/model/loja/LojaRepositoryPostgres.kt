package com.pscarpellini.model.loja

import com.pscarpellini.db.LojaDAO
import com.pscarpellini.db.LojasTable
import com.pscarpellini.db.lojaDaoToModel
import com.pscarpellini.session.Sessao
import com.pscarpellini.suspendTransaction

class LojaRepositoryPostgres : LojaRepository {
//    override suspend fun validarLogin(username: String, password: String): Boolean = suspendTransaction {
//        val loja = LojaDAO
//            .find { (LojasTable.username eq username) }
//            .limit(1)
//            .map(::lojaDaoToModel)
//            .firstOrNull()
//
//        if (loja?.password == password) {
//            Sessao.loja = loja
//            true
//        } else false
//    }
}