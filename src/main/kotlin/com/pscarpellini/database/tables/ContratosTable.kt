package com.pscarpellini.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object ContratosTable : IntIdTable("contratos") {
    val preco = decimal("preco", 10, 2)
    val clienteId = reference("cliente_id", ClientesTable)
    val servicoId = reference("servico_id", ServicosTable)
    val dataContrato = datetime("data_contrato").clientDefault { LocalDateTime.now() }
}
