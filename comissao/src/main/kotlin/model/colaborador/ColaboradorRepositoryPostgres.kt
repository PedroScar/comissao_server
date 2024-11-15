package model.colaborador

import extensions.suspendTransaction
import daos.ColaboradorDAO

class ColaboradorRepositoryPostgres : ColaboradorRepository {
    override suspend fun adicionarUsuario(colaborador: Colaborador): Boolean = suspendTransaction {
        runCatching {
            ColaboradorDAO.new {
                idloja = colaborador.idloja
                nome = colaborador.nome
                endereco = colaborador.endereco
                cpf = colaborador.doc
                email = colaborador.email
                telefone = colaborador.telefone
                status = colaborador.status
            }
            true
        }.getOrElse { false }
    }
}