package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ServicosTable : IntIdTable("servicos") {
    val nome = varchar("nome", 255)
    val dataCriacao = datetime("data_criacao").clientDefault { LocalDateTime.now() }
}