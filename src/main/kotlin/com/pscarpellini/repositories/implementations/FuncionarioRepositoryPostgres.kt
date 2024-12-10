package com.pscarpellini.repositories.implementations

import com.pscarpellini.repositories.interfaces.FuncionarioRepository

class FuncionarioRepositoryPostgres : FuncionarioRepository {
//    override suspend fun adicionarUsuario(funcionario: Funcionario): Boolean = suspendTransaction {
//        runCatching {
//            FuncionarioDAO.new {
//                idloja = Sessao.idPai!!
//                nome = funcionario.nome
//                endereco = funcionario.endereco
//                cpf = funcionario.doc
//                email = funcionario.email
//                telefone = funcionario.telefone
//                status = funcionario.status
//            }
//            true
//        }.getOrElse { false }
//    }
}