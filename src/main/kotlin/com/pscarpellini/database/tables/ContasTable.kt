package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ContasTable : IntIdTable("contas") {
    val clienteId = reference("cliente_id", ClientesTable)
    val tipoConta = varchar("tipo_conta", 50).check { it inList listOf("ADMINISTRADOR", "FUNCIONARIO", "COLABORADOR") }
    val nome = varchar("nome", 255)
    val cpf = varchar("cpf", 100)
    val endereco = varchar("endereco", 255)
    val email = varchar("email", 100)
    val telefone = varchar("telefone", 255)
    val status = varchar("status", 255)
    val usuario = varchar("usuario", 100).uniqueIndex()
    val senha = varchar("senha", 255)
    val dataCriacao = datetime("data_criacao").clientDefault { LocalDateTime.now() }
}