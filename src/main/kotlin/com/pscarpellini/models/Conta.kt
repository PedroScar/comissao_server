package com.pscarpellini.models

import java.time.LocalDateTime

data class Conta(
    val id: Int,
    val cliente: Cliente,
    val tipoConta: String,
    val nome: String,
    val cpf: String,
    val endereco: String,
    val email: String,
    val telefone: String,
    val status: String,
    val usuario: String,
    val senha: String,
    val dataCriacao: LocalDateTime
)
