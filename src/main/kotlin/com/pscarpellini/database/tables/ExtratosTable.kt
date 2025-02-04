package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ExtratosTable : IntIdTable("extratos") {
    val contaResponsavelId = reference("conta_responsavel_id", ContasTable)
    val contaSaldoId = reference("conta_saldo_id", ContasTable)
    val promocaoId = reference("promocao_id", PromocoesTable).nullable()
    val dataCriacao = datetime("data_criacao").clientDefault { LocalDateTime.now() }
    val valor = double("valor")
    val isCredito = bool("is_credito")
}
