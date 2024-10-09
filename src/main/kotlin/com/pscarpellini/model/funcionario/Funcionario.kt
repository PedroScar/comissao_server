package com.pscarpellini.model.funcionario

import com.pscarpellini.model.Conta
import kotlinx.serialization.Serializable

@Serializable
data class Funcionario(
    val idloja: Int,
    override val nome: String,
    override val endereco: String,
    override val doc: String,
    override val email: String,
    override val telefone: String,
    override val status: Int
) : Conta