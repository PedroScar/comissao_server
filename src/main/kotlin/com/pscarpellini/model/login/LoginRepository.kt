package com.pscarpellini.model.login

import com.pscarpellini.model.Conta

interface LoginRepository {
    suspend fun validarLogin(username: String, password: String): Boolean
    suspend fun criarLogin(username: String, password: String, tipo: Int): Boolean
}