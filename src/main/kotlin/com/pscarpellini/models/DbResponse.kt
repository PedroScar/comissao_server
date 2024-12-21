package com.pscarpellini.models

sealed class DbResponse<T>(
    val data: T? = null,
    val mensagem: String? = null
) {
    class Erro<T>(data: T? = null, message: String) : DbResponse<T>(data, message)
    class Successo<T>(data: T) : DbResponse<T>(data)
}