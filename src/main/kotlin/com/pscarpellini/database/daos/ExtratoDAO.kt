package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.ExtratosTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ExtratoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ExtratoDAO>(ExtratosTable)

    var contaResponsavelId by ExtratosTable.contaResponsavelId
    var contaSaldoId by ExtratosTable.contaSaldoId
    var promocaoId by ExtratosTable.promocaoId
    var dataCriacao by ExtratosTable.dataCriacao
    var valor by ExtratosTable.valor
    var isCredito by ExtratosTable.isCredito
}