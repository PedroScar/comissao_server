package com.pscarpellini.model.loja

import com.pscarpellini.enums.ContaStatusEnum
import com.pscarpellini.model.Conta
import kotlinx.serialization.Serializable

@Serializable
data class Loja(
    val id: Int,
    override   val nome: String,
    override   val endereco: String,
    override   val doc: String,
    override   val email: String,
    override   val telefone: String,
    override   val status: ContaStatusEnum
) : Conta
