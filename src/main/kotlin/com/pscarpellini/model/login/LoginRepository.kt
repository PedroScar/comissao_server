package com.pscarpellini.model.login

interface LoginRepository {
    suspend fun validarLogin(username: String, password: String): Boolean
}