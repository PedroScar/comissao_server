package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class SessaoUsuarioVO(
    val email: String? = null,
    val nome: String? = null,
    val cliente: String? = null,
//    val tipoConta: TipoContaEnum,
//    val permissoes: List<PermissoesEnum> = arrayListOf(
//        ""
//    )
)