package com.pscarpellini.model

import com.pscarpellini.enums.ContaStatusEnum

interface Conta {
    val nome: String
    val endereco: String
    val doc: String
    val email: String
    val telefone: String
    val status: ContaStatusEnum
}