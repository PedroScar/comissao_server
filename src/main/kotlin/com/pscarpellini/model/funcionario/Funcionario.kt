package com.pscarpellini.model.funcionario

import com.pscarpellini.enums.ContaStatusEnum
import kotlinx.serialization.Serializable

@Serializable
data class Funcionario(
    val idLoja: Int,
    val nome: String,
    val endereco: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val status: ContaStatusEnum
)