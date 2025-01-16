package com.pscarpellini.extensions

import com.pscarpellini.frontend.enums.CategoriasMenuEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ArrayList<IItensMenuEnum>.obterListaGeral(): ArrayList<IItensMenuEnum> {
    val listaGeral = arrayListOf<IItensMenuEnum>()

    this.forEach { elemento ->
        listaGeral.add(elemento)
        if (elemento is CategoriasMenuEnum) {
            listaGeral.addAll(elemento.itens)
        }
    }

    return listaGeral
}