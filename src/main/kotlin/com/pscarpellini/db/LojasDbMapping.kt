package com.pscarpellini.db

import com.pscarpellini.enums.ContaStatusEnum
import com.pscarpellini.model.loja.Loja
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LojasTable : IntIdTable("lojas") {
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cnpj = varchar("cnpj", 255)
    val email = varchar("email", 255).uniqueIndex()
    val telefone = varchar("telefone", 255)
    val status = varchar("status", 255)
}

class LojaDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LojaDAO>(LojasTable)

    val nome by LojasTable.nome
    val endereco by LojasTable.endereco
    val cnpj by LojasTable.cnpj
    val email by LojasTable.email
    val telefone by LojasTable.telefone
    val status by LojasTable.status
}

fun lojaDaoToModel(dao: LojaDAO) = Loja(
    dao.id.value,
    dao.nome,
    dao.endereco,
    dao.cnpj,
    dao.email,
    dao.telefone,
    ContaStatusEnum.valueOf(dao.status)
)