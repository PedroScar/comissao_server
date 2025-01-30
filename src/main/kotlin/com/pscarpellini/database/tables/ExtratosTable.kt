package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ExtratosTable : IntIdTable("extratos") {
    val contaCriacaoId = reference("conta_criacao_id", ContasTable)
    val contaDonoId = reference("conta_dono_id", ContasTable)
    val promocaoId = reference("promocao_id", PromocoesTable)
    val dataCriacao = datetime("data_criacao").clientDefault { LocalDateTime.now() }
    val valor = double("valor")
    val isCredito = bool("is_credito")
}
