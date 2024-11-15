package daos

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.LoginTable

class LoginDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LoginDAO>(LoginTable)

    var idpai by LoginTable.idpai
    var tipo by LoginTable.tipo
    var username by LoginTable.username
    var pwd by LoginTable.pwd
}