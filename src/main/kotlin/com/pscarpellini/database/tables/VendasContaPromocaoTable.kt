package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object VendasContaPromocaoTable  : IntIdTable("vendas_por_conta_promocao") {
    val contaId = reference("conta_id", ContasTable)
    val promocaoId = reference("promocao_id", PromocoesTable)
    val adicionadoPorId = reference("adicionado_por", ContasTable)
    val dataTransacao = datetime("data_transacao").clientDefault { LocalDateTime.now() }
}