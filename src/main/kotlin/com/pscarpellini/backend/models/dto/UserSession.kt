package com.pscarpellini.backend.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val email: String? = null,
    val nome: String? = null,
    val cliente: String? = null,
//    val tipoConta: TipoContaEnum,
//    val permissoes: List<PermissoesEnum> = arrayListOf(
//        ""
//    )
)