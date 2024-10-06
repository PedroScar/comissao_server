package com.pscarpellini.model.colaborador

import com.pscarpellini.enums.ContaStatusEnum
import kotlinx.serialization.Serializable

@Serializable
data class Colaborador(
    val idLoja: Int,
    val nome: String,
    val endereco: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val status: ContaStatusEnum
)