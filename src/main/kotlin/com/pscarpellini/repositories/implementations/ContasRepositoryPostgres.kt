package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.daos.PromocaoDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.PromocoesTable
import com.pscarpellini.database.utils.promocaoDaoToModel
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ContasRepositoryPostgres : ContasRepository {
    override suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO> = suspendTransaction {
        val conta = ContaDAO
            .find { (ContasTable.usuario eq usuario) }
            .limit(1)
            .firstOrNull()

        return@suspendTransaction if (conta == null) {
            DbResponse.Erro(null, "Usuário não encontrado: $senha - $usuario")
        } else if (conta.senha == senha) {
            DbResponse.Successo(contaDaoToModel(conta))
        } else {
            DbResponse.Erro(null, "Senha incorreta: $senha - $usuario")
        }
    }

    override suspend fun carregarUsuarios(clienteId: Int): DbResponse<List<ContaVO>> = suspendTransaction {
        val listaUsuarios = ContaDAO
            .find { (ContasTable.clienteId eq clienteId) }
            .map(::contaDaoToModel)

        DbResponse.Successo(listaUsuarios)
    }
}