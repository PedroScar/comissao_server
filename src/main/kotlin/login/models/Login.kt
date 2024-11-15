package model.login.models

import daos.LoginDAO
import enums.ContaTipoEnum
import enums.ContaTipoEnum.Companion.getTipo

data class Login(
    var idpai: Int,
    var tipo: ContaTipoEnum,
    var username: String,
    var password: String,
)

fun loginDaoToModel(dao: LoginDAO) = Login(
    dao.idpai,
    getTipo(dao.tipo),
    dao.username,
    dao.pwd,
)