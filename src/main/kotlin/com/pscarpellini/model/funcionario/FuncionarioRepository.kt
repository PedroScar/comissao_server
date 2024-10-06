package com.pscarpellini.model.funcionario

interface FuncionarioRepository {
    suspend fun adicionarUsuario(funcionario: Funcionario): Boolean
}