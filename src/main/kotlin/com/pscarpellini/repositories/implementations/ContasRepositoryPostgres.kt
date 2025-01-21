package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.suspendTransaction
import java.time.LocalDateTime

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
        val cliente = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID $clienteId não encontrado")

        val listaUsuarios = ContaDAO
            .find { (ContasTable.clienteId eq cliente.id) }
            .map(::contaDaoToModel)

        DbResponse.Successo(listaUsuarios)
    }

    override suspend fun criarUsuario(conta: ContaVO): DbResponse<ContaVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(conta.cliente.id)
            ?: throw IllegalArgumentException("Cliente com ID ${conta.cliente.id} não encontrado")

        runCatching {
            ContaDAO.new {
                clienteId = cliente
                tipoConta = conta.tipoConta
                nome = conta.nome
                cpf = conta.cpf
                endereco = conta.endereco
                email = conta.email
                telefone = conta.telefone
                saldo = conta.saldo
                status = conta.status
                usuario = conta.usuario
                senha = "12345678"
                dataCriacao = LocalDateTime.now()
            }
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.onSuccess {
            DbResponse.Successo(conta)
        }
        DbResponse.Successo(conta)
//        DbResponse.Erro(message = "Falha ao adicionar conta: ${it.cause} | ${it.stackTrace}")
    }
}