package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object PerfisDeAcessoTable : IntIdTable("perfis_de_acessos") {
    val clienteId = reference("cliente_id", ClientesTable)
    val nome = varchar("nome", 255)
    val descricao = varchar("descricao", 255)
}