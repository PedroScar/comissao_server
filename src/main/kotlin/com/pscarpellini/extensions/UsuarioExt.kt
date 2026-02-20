package com.pscarpellini.extensions

import java.text.Normalizer
import kotlin.random.Random

fun criarNomeDeUsuario(nomeCompleto: String): String {
    val nomes = nomeCompleto.trim().split("\\s+".toRegex())
    if (nomes.size < 2) return normalizar(nomes[0])

    val primeiroNome = normalizar(nomes.first())
    val segundoNome = normalizar(nomes.getOrNull(1) ?: "")

    return "${primeiroNome.first()}$segundoNome"
}

// Função para remover acentuação e transformar em minúsculas
fun normalizar(texto: String): String {
    return Normalizer.normalize(texto, Normalizer.Form.NFD)
        .replace("[^\\p{ASCII}]".toRegex(), "")
        .lowercase()
}

fun gerarSenhaBasica(): String {
    val letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    val numeros = "0123456789"
    val caracteresEspeciais = "!@#$%&*"

    val senha = StringBuilder()

    // Adiciona 6 caracteres entre letras e números
    for (i in 0 until 6) {
        val escolha = Random.nextInt(2) // 0 para letra, 1 para número
        when (escolha) {
            0 -> senha.append(letras[Random.nextInt(letras.length)])
            1 -> senha.append(numeros[Random.nextInt(numeros.length)])
        }
    }

    // Adiciona 1 caractere especial
    senha.append(caracteresEspeciais[Random.nextInt(caracteresEspeciais.length)])

    // Adiciona 1 caractere adicional (letra ou número)
    val escolhaFinal = Random.nextInt(2) // 0 para letra, 1 para número
    when (escolhaFinal) {
        0 -> senha.append(letras[Random.nextInt(letras.length)])
        1 -> senha.append(numeros[Random.nextInt(numeros.length)])
    }

    // Embaralha a senha para garantir que o caractere especial não esteja sempre no final
    return senha.toString().toCharArray().apply { shuffle() }.joinToString("")
}