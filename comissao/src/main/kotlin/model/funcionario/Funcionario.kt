package model.funcionario

import daos.FuncionarioDAO
import models.Conta

@kotlinx.serialization.Serializable
data class Funcionario(
    val idloja: Int,
    override val nome: String,
    override val endereco: String,
    override val doc: String,
    override val email: String,
    override val telefone: String,
    override val status: Int
) : Conta

fun funcionarioDaoToModel(dao: FuncionarioDAO) = Funcionario(
    dao.idloja,
    dao.nome,
    dao.endereco,
    dao.cpf,
    dao.email,
    dao.telefone,
    dao.status
)