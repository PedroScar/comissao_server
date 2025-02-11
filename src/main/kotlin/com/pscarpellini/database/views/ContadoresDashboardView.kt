package com.pscarpellini.database.views

import com.pscarpellini.database.tables.ClientesTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ContadoresDashboardView : Table("view_contadores_dashboard") {
    val clienteId = reference("cliente_id", ClientesTable)
    val quantidadePromocoesAtivas = integer("quantidade_promocoes_ativas")
    val quantidadePromotores = integer("quantidade_promotores")
    val valorComissoesMesAtual = double("valor_comissoes_mes_atual")
}