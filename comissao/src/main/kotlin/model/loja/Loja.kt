package model.loja

import daos.LojaDAO
import models.Conta

@kotlinx.serialization.Serializable
data class Loja(
    val id: Int,
    override val nome: String,
    override val endereco: String,
    override val doc: String,
    override val email: String,
    override val telefone: String,
    override val status: Int
) : Conta

fun lojaDaoToModel(dao: LojaDAO) = Loja(
    dao.id.value,
    dao.nome,
    dao.endereco,
    dao.cnpj,
    dao.email,
    dao.telefone,
    dao.status
)