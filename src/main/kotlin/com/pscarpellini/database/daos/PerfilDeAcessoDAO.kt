package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.PerfisDeAcessoTable
import com.pscarpellini.database.tables.PromocoesTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PerfilDeAcessoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PerfilDeAcessoDAO>(PerfisDeAcessoTable)

    var clienteId by ClienteDAO referencedOn PerfisDeAcessoTable.clienteId
    var nome by PerfisDeAcessoTable.nome
    var descricao by PerfisDeAcessoTable.descricao
}