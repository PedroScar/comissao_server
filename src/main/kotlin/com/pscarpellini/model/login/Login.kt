package com.pscarpellini.model.login

import com.pscarpellini.enums.ContaTipoEnum

data class Login(
    val idpai: Int,
    val tipo: ContaTipoEnum,
    val username: String,
    val password: String,
)