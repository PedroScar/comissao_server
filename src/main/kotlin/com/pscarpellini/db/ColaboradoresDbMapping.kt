package com.pscarpellini.db

import com.pscarpellini.enums.ContaStatusEnum
import com.pscarpellini.model.colaborador.Colaborador
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ColaboradoresTable : IntIdTable("colaboradores") {
    val iden = integer("id")
    val idloja = integer("idloja")
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cpf = varchar("cpf", 255)
    val email = varchar("email", 255)
    val telefone = varchar("telefone", 255)
    val status = varchar("status", 255)
}

class ColaboradorDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ColaboradorDAO>(ColaboradoresTable)

    var iden by ColaboradoresTable.iden
    var idloja by  ColaboradoresTable.idloja
    var nome by ColaboradoresTable.nome
    var endereco by ColaboradoresTable.endereco
    var cpf by ColaboradoresTable.cpf
    var email by ColaboradoresTable.email
    var telefone by ColaboradoresTable.telefone
    var status by ColaboradoresTable.status
}

fun colaboradorDaoToModel(dao: ColaboradorDAO) = Colaborador(
    dao.iden,
    dao.idloja,
    dao.nome,
    dao.endereco,
    dao.cpf,
    dao.email,
    dao.telefone,
    ContaStatusEnum.valueOf(dao.status)
)