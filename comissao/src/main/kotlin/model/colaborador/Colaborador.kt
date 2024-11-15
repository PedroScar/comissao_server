package model.colaborador

import daos.ColaboradorDAO
import kotlinx.serialization.Serializable
import models.Conta

@Serializable
data class Colaborador(
    val idloja: Int,
    override val nome: String,
    override val endereco: String,
    override val doc: String,
    override val email: String,
    override val telefone: String,
    override val status: Int
) : Conta

fun colaboradorDaoToModel(dao: ColaboradorDAO) = Colaborador(
    dao.idloja,
    dao.nome,
    dao.endereco,
    dao.cpf,
    dao.email,
    dao.telefone,
    dao.status
)