package com.pscarpellini.repositories.implementations

import com.pscarpellini.database.daos.ClienteDAO
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.daos.ContaDAO
import com.pscarpellini.database.tables.ContasTable
import com.pscarpellini.database.tables.SaldosTable
import com.pscarpellini.database.utils.contaDaoToModel
import com.pscarpellini.database.utils.contaESaldoToModel
import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaESaldoVO
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.suspendTransaction
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime

class ContasRepositoryPostgres : ContasRepository {
    override suspend fun validarLogin(usuario: String, senha: String): DbResponse<ContaVO> = suspendTransaction {
        val conta = ContaDAO
            .find {
                (ContasTable.usuario.lowerCase().eq(usuario.lowercase()))
                    .or(ContasTable.email.lowerCase().eq(usuario.lowercase()))
            }
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

    override suspend fun carregarPromotor(promotorId: Int, clienteId: Int): DbResponse<ContaESaldoVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID $clienteId não encontrado")

        val aliasSaldo = SaldosTable.alias("saldo")

        val conta = ContasTable
            .join(aliasSaldo, JoinType.INNER, additionalConstraint = { aliasSaldo[SaldosTable.contaId] eq ContasTable.id })
            .selectAll()
            .where {
                (ContasTable.clienteId eq cliente.id)
                    .and(ContasTable.id eq promotorId)
            }
            .limit(1)
            .firstOrNull()

        return@suspendTransaction if (conta == null) {
            DbResponse.Erro(null, "Usuário não encontrado: $promotorId")
        } else {
            DbResponse.Successo(contaESaldoToModel(conta, aliasSaldo))
        }
    }

    override suspend fun carregarUsuarios(nome: String, clienteId: Int): DbResponse<List<ContaVO>> = suspendTransaction {
        val cliente = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID $clienteId não encontrado")

        val listaUsuarios = ContaDAO
            .find {
                (ContasTable.clienteId eq cliente.id)
                    .and(
                        (ContasTable.nome.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.usuario.lowerCase().like("%${nome.lowercase()}%"))
                            .or(ContasTable.email.lowerCase().like("%${nome.lowercase()}%"))
                    )
            }
            .map(::contaDaoToModel)

        DbResponse.Successo(listaUsuarios)
    }

    override suspend fun listarPromotores(clienteId: Int): DbResponse<List<ContaVO>> = suspendTransaction {
        val cliente = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID $clienteId não encontrado")

        val listaUsuarios = ContaDAO
            .find {
                (ContasTable.clienteId eq cliente.id)
                    .and { ContasTable.tipoConta.lowerCase() eq PerfisDeAcessoEnum.PROMOTOR.slug }
            }
            .map(::contaDaoToModel)

        DbResponse.Successo(listaUsuarios)
    }

    override suspend fun carregarUsuario(usuarioId: Int, clienteId: Int): DbResponse<ContaVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(clienteId)
            ?: throw IllegalArgumentException("Cliente com ID $clienteId não encontrado")

        val conta = ContaDAO
            .find {
                (ContasTable.clienteId eq cliente.id)
                    .and(
                        (ContasTable.id eq usuarioId)
                    )
            }
            .limit(1)
            .firstOrNull()

        return@suspendTransaction if (conta == null) {
            DbResponse.Erro(null, "Usuário não encontrado: $usuarioId")
        } else {
            DbResponse.Successo(contaDaoToModel(conta))
        }
    }

    override suspend fun criarUsuario(conta: ContaVO): DbResponse<ContaVO> = suspendTransaction {
        val cliente = ClienteDAO.findById(conta.cliente?.id ?: -1)
            ?: throw IllegalArgumentException("Cliente com ID ${conta.cliente?.id} não encontrado")

        runCatching {
            ContaDAO.new {
                clienteId = cliente
                tipoConta = conta.tipoConta
                nome = conta.nome
                cpf = conta.cpf
                endereco = conta.endereco
                email = conta.email.lowercase()
                telefone = conta.telefone
                status = conta.status
                usuario = conta.usuario.lowercase()
                senha = conta.senha ?: "12345678"
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
    }

    override suspend fun editarUsuario(conta: ContaVO): DbResponse<ContaVO> = suspendTransaction {
            val cliente = ClienteDAO.findById(conta.cliente?.id!!)
                ?: throw IllegalArgumentException("Cliente com ID ${conta.cliente?.id} não encontrado")

            val contaExistente = ContaDAO.findById(conta.id ?: -1)
                ?: return@suspendTransaction DbResponse.Erro(null, "Conta com ID ${conta.id} não encontrado")

            runCatching {
                contaExistente.apply {
                    clienteId = cliente
                    tipoConta = conta.tipoConta
                    nome = conta.nome
                    cpf = conta.cpf
                    endereco = conta.endereco
                    email = conta.email.lowercase()
                    telefone = conta.telefone
                    status = conta.status
                    usuario = conta.usuario.lowercase()
                    senha = conta.senha ?: "12345678"
                    dataCriacao = LocalDateTime.now()
                }
                DbResponse.Successo(contaDaoToModel(contaExistente))
            }.onFailure {
                println("==================")
                println("ERRO DB: ${it.message}")
                println("==================")
                DbResponse.Erro(null, message = it.message.toString())
            }.getOrDefault(DbResponse.Erro(null, message = "Ops... algo de errado aconteceu!"))
        }

    override suspend fun validarEmailEsqueciMinhaSenha(emailOuUsuario: String): Pair<String, Boolean> = suspendTransaction {
        val cliente = ContaDAO.find {
            ContasTable.usuario.lowerCase()
                .eq(emailOuUsuario.lowercase())
                .or { ContasTable.email.lowerCase()
                    .eq(emailOuUsuario.lowercase()) }
        }.limit(1).firstOrNull()
            ?: throw IllegalArgumentException("Cliente não encontrado")
        cliente.email to true
    }

    override suspend fun definirSenhaProvisoria(email: String, novaSenha: String): Boolean = suspendTransaction {
        runCatching {
            ContaDAO
                .find { ContasTable.email.lowerCase().eq(email.lowercase()) }
                .limit(1)
                .firstOrNull()
                ?.apply { senha = novaSenha }
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.getOrNull() != null
    }

    override suspend fun definirSenha(usuarioId: Int, novaSenha: String): Boolean = suspendTransaction {
        runCatching {
            ContaDAO
                .findById(usuarioId)
                ?.apply { senha = novaSenha }
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.getOrNull() != null
    }

    override suspend fun atualizarMeuPerfil(usuarioId: Int, telefone: String, imagemDePerfil: String): Boolean = suspendTransaction {
        runCatching {
            ContaDAO
                .findById(usuarioId)
                ?.apply {
                    this.telefone = telefone
                    this.imagem = imagemDePerfil
                }
        }.onFailure {
            println("=============================================================================")
            println("ERRO AQUI: ${it.stackTrace}")
            println("=============================================================================")
        }.getOrNull() != null
    }
}