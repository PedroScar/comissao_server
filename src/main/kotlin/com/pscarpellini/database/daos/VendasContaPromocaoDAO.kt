package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.VendasContaPromocaoTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class VendasContaPromocaoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<VendasContaPromocaoDAO>(VendasContaPromocaoTable)

    var contaId by ContaDAO referencedOn VendasContaPromocaoTable.contaId
    var promocaoId by PromocaoDAO referencedOn VendasContaPromocaoTable.promocaoId
    var adicionadoPorId by ContaDAO referencedOn VendasContaPromocaoTable.adicionadoPorId
    var dataTransacao by VendasContaPromocaoTable.dataTransacao
}