package com.pscarpellini.model.colaborador

interface ColaboradorRepository {
    suspend fun adicionarUsuario(funcionario: Colaborador): Boolean
}