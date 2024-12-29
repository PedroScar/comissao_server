package com.pscarpellini.database.daos

import com.pscarpellini.database.tables.PromocoesTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PromocaoDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PromocaoDAO>(PromocoesTable)

    var clienteId by PromocaoDAO referencedOn PromocoesTable.clienteId
    var titulo by PromocoesTable.titulo
    var subtitulo by PromocoesTable.subtitulo
    var conteudo by PromocoesTable.conteudo
    var imagem by PromocoesTable.imagem
    var dataValidade  by PromocoesTable.dataValidade
    var dataCriacao by PromocoesTable.dataCriacao
}