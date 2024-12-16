package com.pscarpellini.backend.database.daos

import com.pscarpellini.backend.database.tables.ContratosTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ContratoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ContratoDAO>(ContratosTable)

    var preco by ContratosTable.preco
    var clienteId by ClienteDAO referencedOn ContratosTable.clienteId
    var servicoId by ServicoDAO referencedOn ContratosTable.servicoId
    var dataContrato by ContratosTable.dataContrato
}