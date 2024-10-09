package com.pscarpellini.db

import com.pscarpellini.enums.ContaTipoEnum
import com.pscarpellini.model.login.Login
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LoginTable : IntIdTable("login") {
    val idpai = integer("idpai")
    val tipo = integer("tipo")
    val username = varchar("username", 255)
    val pwd = varchar("pwd", 255)
}

class LoginDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LoginDAO>(LoginTable)

    var idpai by LoginTable.idpai
    var tipo by LoginTable.tipo
    var username by LoginTable.username
    var pwd by LoginTable.pwd
}

fun loginDaoToModel(dao: LoginDAO) = Login(
    dao.idpai,
    ContaTipoEnum.getTipo(dao.tipo),
    dao.username,
    dao.pwd,
)