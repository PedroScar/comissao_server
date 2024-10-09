package com.pscarpellini.model.login

import com.pscarpellini.enums.ContaTipoEnum

data class Login(
    var idpai: Int,
    var tipo: ContaTipoEnum,
    var username: String,
    var password: String,
)