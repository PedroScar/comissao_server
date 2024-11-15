package model.funcionario

import extensions.suspendTransaction
import Sessao
import daos.FuncionarioDAO

class FuncionarioRepositoryPostgres : FuncionarioRepository {
    override suspend fun adicionarUsuario(funcionario: Funcionario): Boolean = suspendTransaction {
        runCatching {
           FuncionarioDAO.Companion.new {
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