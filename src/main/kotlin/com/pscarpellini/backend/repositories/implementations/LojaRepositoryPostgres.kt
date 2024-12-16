package com.pscarpellini.backend.repositories.implementations

import com.pscarpellini.backend.repositories.interfaces.LojaRepository

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