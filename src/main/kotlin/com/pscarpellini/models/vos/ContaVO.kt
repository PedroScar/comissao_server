package com.pscarpellini.models.vos

import kotlinx.serialization.Serializable

@Serializable
data class ContaVO(
    val id: Int? = null,
    var cliente: ClienteVO?,
    val nome: String,
    val foto: String = "",
    val endereco: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val usuario: String,
    val senha: String? = null,
    val status: String,
    val tipoConta: String,
    val imagemDePerfil: String?
)
