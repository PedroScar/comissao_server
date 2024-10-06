package com.pscarpellini.db

import com.pscarpellini.model.login.Login
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object LoginLojaTable : IntIdTable("loginloja") {
    val idPai = integer("idpai")
    val username = varchar("username", 255)
    val password = varchar("password", 255)
}

class LoginLojaDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<LoginLojaDAO>(LoginLojaTable)

    var idPai by  LoginLojaTable.idPai
    var username by LoginLojaTable.username
    var password by LoginLojaTable.password
}

fun loginLojaDaoToModel(dao: LoginLojaDAO) = Login(
    dao.idPai,
    dao.username,
    dao.password,
)