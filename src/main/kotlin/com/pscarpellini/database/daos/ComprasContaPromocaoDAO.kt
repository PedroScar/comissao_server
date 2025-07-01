package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.ComprasContaPromocaoTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ComprasContaPromocaoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ComprasContaPromocaoDAO>(ComprasContaPromocaoTable)

    var contaId by ContaDAO referencedOn ComprasContaPromocaoTable.contaId
    var promocaoId by PromocaoDAO referencedOn ComprasContaPromocaoTable.promocaoId
    var adicionadoPorId by ContaDAO referencedOn ComprasContaPromocaoTable.adicionadoPorId
    var dataTransacao by ComprasContaPromocaoTable.dataTransacao
}