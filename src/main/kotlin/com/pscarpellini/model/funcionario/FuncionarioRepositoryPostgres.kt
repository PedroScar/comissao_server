package com.pscarpellini.model.funcionario

import com.pscarpellini.db.FuncionarioDAO
import com.pscarpellini.session.Sessao
import com.pscarpellini.suspendTransaction

class FuncionarioRepositoryPostgres : FuncionarioRepository {
    override suspend fun adicionarUsuario(funcionario: Funcionario): Boolean = suspendTransaction {
        runCatching {
            FuncionarioDAO.new {
                idloja = Sessao.idPai!!
                nome = funcionario.nome
                endereco = funcionario.endereco
                cpf = funcionario.doc
                email = funcionario.email
                telefone = funcionario.telefone
                status = funcionario.status
            }
            true
        }.getOrElse { false }
    }
}