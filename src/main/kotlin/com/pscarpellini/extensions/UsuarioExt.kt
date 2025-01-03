package com.pscarpellini.extensions

import java.text.Normalizer

fun criarNomeDeUsuario(nomeCompleto: String): String {
    val nomes = nomeCompleto.trim().split("\\s+".toRegex())
    if (nomes.size < 2) return normalizar(nomes[0])

    val primeiroNome = normalizar(nomes.first())
    val segundoNome = normalizar(nomes.getOrNull(1) ?: "")
    val ultimoNome = normalizar(nomes.last())

    return "${primeiroNome.first()}$segundoNome"
}

// Função para remover acentuação e transformar em minúsculas
fun normalizar(texto: String): String {
    return Normalizer.normalize(texto, Normalizer.Form.NFD)
        .replace("[^\\p{ASCII}]".toRegex(), "")
        .lowercase()
}