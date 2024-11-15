package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object LojasTable : IntIdTable("lojas") {
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cnpj = varchar("cnpj", 255)
    val email = varchar("email", 255)
    val telefone = varchar("telefone", 255)
    val status = integer("status")
}