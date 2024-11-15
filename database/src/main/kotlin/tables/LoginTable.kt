package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object LoginTable : IntIdTable("models/login") {
    val idpai = integer("idpai")
    val tipo = integer("tipo")
    val username = varchar("username", 255)
    val pwd = varchar("pwd", 255)
}
