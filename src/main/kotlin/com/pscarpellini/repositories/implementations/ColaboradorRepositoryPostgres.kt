package com.pscarpellini.repositories.implementations

import com.pscarpellini.repositories.interfaces.ColaboradorRepository

class ColaboradorRepositoryPostgres : ColaboradorRepository {
//    override suspend fun adicionarUsuario(funcionario: Colaborador): Boolean = suspendTransaction {
//        runCatching {
//            FuncionarioDAO.new {
//                idloja = funcionario.idloja
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