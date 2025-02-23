package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.ClientesTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ClienteDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ClienteDAO>(ClientesTable)

    var nome by ClientesTable.nome
    var endereco by ClientesTable.endereco
    var cnpj by ClientesTable.cnpj
    var email by ClientesTable.email
    var telefone by ClientesTable.telefone
    var status by ClientesTable.status
    var dataCriacao by ClientesTable.dataCriacao
    var logo by ClientesTable.logo
}