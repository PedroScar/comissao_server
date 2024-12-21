package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.contaDaoToModel
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.suspendTransaction

class ContasRepositoryPostgres : ContasRepository {
    override suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO> = suspendTransaction {
        val conta = ContaDAO
            .find { (ContasTable.usuario eq usuario) }
            .limit(1)
            .map(::contaDaoToModel)
            .firstOrNull()

        return@suspendTransaction if (conta == null) {
            DbResponse.Erro(null, "Usuário não encontrado")
        } else if (conta.senha == senha) {
            DbResponse.Successo(conta)
        } else {
            DbResponse.Erro(null, "Senha incorreta")
        }
    }
}