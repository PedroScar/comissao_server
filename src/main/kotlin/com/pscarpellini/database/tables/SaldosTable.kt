package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object SaldosTable : IntIdTable("saldos") {
    val contaId = reference("conta_id", ContasTable)
    val saldo = double("saldo")
}