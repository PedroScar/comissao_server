package model.login.models

import extensions.suspendTransaction
import Sessao
import daos.LoginDAO
import tables.LoginTable

class LoginRepositoryPostgres : LoginRepository {
    override suspend fun validarLogin(username: String, password: String): Boolean = suspendTransaction {
        val login = LoginDAO.Companion
            .find { (LoginTable.username eq username) }
            .limit(1)
            .map(::loginDaoToModel)
            .firstOrNull()

        if (login?.password == password) {
            Sessao.tipo = login.tipo
           // Sessao.conta =
            true
        } else false
    }

    override suspend fun criarLogin(
        mUsername: String,
        mPassword: String,
        mTipo: Int
    ): Boolean = suspendTransaction {
        runCatching {
            LoginDAO.Companion.new {
                idpai = Sessao.idPai!!
                tipo = mTipo
                username = mUsername
                pwd = mPassword
            }
            true
        }.getOrElse { false }
    }
}