package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.SaldosTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SaldoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SaldoDAO>(SaldosTable)

    var contaId by ContaDAO referencedOn SaldosTable.contaId
    var saldo by SaldosTable.saldo
}