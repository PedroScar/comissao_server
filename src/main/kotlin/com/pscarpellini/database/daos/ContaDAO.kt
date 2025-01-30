package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.ContasTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ContaDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ContaDAO>(ContasTable)

    var clienteId by ClienteDAO referencedOn ContasTable.clienteId
    var tipoConta by ContasTable.tipoConta
    var nome by ContasTable.nome
    var cpf by ContasTable.cpf
    var endereco by ContasTable.endereco
    var email by ContasTable.email
    var telefone by ContasTable.telefone
    var status by ContasTable.status
    var usuario by ContasTable.usuario
    var senha by ContasTable.senha
    var dataCriacao by ContasTable.dataCriacao
}