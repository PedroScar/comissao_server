package com.pscarpellini.model.login


import com.pscarpellini.db.LoginLojaDAO
import com.pscarpellini.db.LoginLojaTable
import com.pscarpellini.db.LojaDAO
import com.pscarpellini.db.LojasTable
import com.pscarpellini.db.loginLojaDaoToModel
import com.pscarpellini.db.lojaDaoToModel
import com.pscarpellini.session.Sessao
import com.pscarpellini.suspendTransaction

class LoginRepositoryPostgres : LoginRepository {
    override suspend fun validarLogin(username: String, password: String): Boolean = suspendTransaction {
        val loginLoja = LoginLojaDAO
            .find { (LoginLojaTable.username eq username) }
            .limit(1)
            .map(::loginLojaDaoToModel)
            .firstOrNull()

        if (loginLoja?.password == password) {
            Sessao.loja = LojaDAO
                .find { (LojasTable.id eq loginLoja.idPai) }
                .map(::lojaDaoToModel)
                .firstOrNull()
            true
        } else false
    }
}