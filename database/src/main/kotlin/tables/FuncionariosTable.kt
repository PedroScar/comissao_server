package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object FuncionariosTable : IntIdTable("funcionarios") {
    val idloja = integer("idloja")
    val nome = varchar("nome", 255)
    val endereco = varchar("endereco", 255)
    val cpf = varchar("cpf", 255)
    val email = varchar("email", 255)
    val telefone = varchar("telefone", 255)
    val status = integer("status")
}