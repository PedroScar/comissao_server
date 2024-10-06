package com.pscarpellini.enums

enum class ContaStatusEnum {
    ATIVO,
    INATIVO,
    ATRASADO;

    companion object {
        fun getByName(name: String): ContaStatusEnum = entries.find { it.name == name } ?: INATIVO
    }
}