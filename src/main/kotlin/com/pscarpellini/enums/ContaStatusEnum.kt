package com.pscarpellini.enums

enum class ContaStatusEnum(val id: Int) {
    ATIVO(0),
    INATIVO(1),
    ATRASADO(2);

    companion object {
        fun getStatus(name: String): ContaStatusEnum = entries.find { it.name == name } ?: INATIVO
        fun getStatus(id: Int): ContaStatusEnum = entries.find { it.id == id } ?: INATIVO
    }
}