package com.pscarpellini.model.login

import com.pscarpellini.db.ColaboradorDAO
import com.pscarpellini.db.ColaboradoresTable
import com.pscarpellini.db.FuncionarioDAO
import com.pscarpellini.db.FuncionariosTable
import com.pscarpellini.db.LoginDAO
import com.pscarpellini.db.LoginTable
import com.pscarpellini.db.LojaDAO
import com.pscarpellini.db.LojasTable
import com.pscarpellini.db.colaboradorDaoToModel
import com.pscarpellini.db.funcionarioDaoToModel
import com.pscarpellini.db.loginDaoToModel
import com.pscarpellini.db.lojaDaoToModel
import com.pscarpellini.enums.ContaTipoEnum
import com.pscarpellini.model.Conta
import com.pscarpellini.session.Sessao
import com.pscarpellini.suspendTransaction

class LoginRepositoryPostgres : LoginRepository {
    override suspend fun validarLogin(username: String, password: String): Boolean = suspendTransaction {
        val login = LoginDAO
            .find { (LoginTable.username eq username) }
            .limit(1)
            .map(::loginDaoToModel)
            .firstOrNull()

        if (login?.password == password) {
            Sessao.tipo = login.tipo
            Sessao.conta = when (login.tipo) {
                ContaTipoEnum.ADMIN -> LojaDAO
                    .find { (LojasTable.id eq login.idpai) }
                    .map(::lojaDaoToModel)
                    .firstOrNull()

                ContaTipoEnum.FUNCIONARIO -> FuncionarioDAO
                    .find { (FuncionariosTable.id eq login.idpai) }
                    .map(::funcionarioDaoToModel)
                    .firstOrNull()

                ContaTipoEnum.COLABORADOR -> ColaboradorDAO
                    .find { (ColaboradoresTable.id eq login.idpai) }
                    .map(::colaboradorDaoToModel)
                    .firstOrNull()
            }
            true
        } else false
    }

    override suspend fun criarLogin(
        mUsername: String,
        mPassword: String,
        mTipo: Int
    ): Boolean = suspendTransaction {
        runCatching {
            LoginDAO.new {
                idpai = Sessao.idPai!!
                tipo = mTipo
                username = mUsername
                pwd = mPassword
            }
            true
        }.getOrElse { false }
    }
}