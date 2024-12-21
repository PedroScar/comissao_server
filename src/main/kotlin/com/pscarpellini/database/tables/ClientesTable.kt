package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object ClientesTable : IntIdTable("clientes") {
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cnpj = varchar("cnpj", 255)
    val email = varchar("email", 100).uniqueIndex()
    val telefone = varchar("telefone", 255)
    val status = varchar("status", 255)
    val dataCriacao = datetime("data_criacao")
}