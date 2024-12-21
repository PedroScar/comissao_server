package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.ServicosTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ServicoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ServicoDAO>(ServicosTable)

    var nome by ServicosTable.nome
    var dataCriacao by ServicosTable.dataCriacao
}