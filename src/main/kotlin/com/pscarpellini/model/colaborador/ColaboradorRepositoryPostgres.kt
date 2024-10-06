package com.pscarpellini.model.colaborador

import com.pscarpellini.db.FuncionarioDAO
import com.pscarpellini.suspendTransaction

class ColaboradorRepositoryPostgres : ColaboradorRepository {
    override suspend fun adicionarUsuario(funcionario: Colaborador): Boolean = suspendTransaction {
        runCatching {
            FuncionarioDAO.new {
                idloja = funcionario.idloja
                nome = funcionario.nome
                endereco = funcionario.endereco
                cpf = funcionario.doc
                email = funcionario.email
                telefone = funcionario.telefone
                status = funcionario.status.name
            }
            true
        }.getOrElse { false }
    }
}