package com.pscarpellini.extensions

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.CategoriasMenuEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.interfaces.IItensMenuEnum

fun ArrayList<IItensMenuEnum>.obterListaGeral(papeisDeAcesso: ArrayList<PapeisDeAcessoEnum>): ArrayList<IItensMenuEnum> {
    val listaGeral = arrayListOf<IItensMenuEnum>()

    this.forEach { elemento ->
        when (elemento) {
            is ItensMenuEnum -> {
                if (papeisDeAcesso.contains(elemento.papelDeAcesso)) listaGeral.add(elemento)
                return@forEach
            }

            is CategoriasMenuEnum -> {
                val itensFiltrados = elemento.itens.filter { papeisDeAcesso.contains(it.papelDeAcesso) }
                if (itensFiltrados.isEmpty()) return@forEach
                listaGeral.add(elemento)
                listaGeral.addAll(itensFiltrados)
            }
        }
    }

    return listaGeral
}