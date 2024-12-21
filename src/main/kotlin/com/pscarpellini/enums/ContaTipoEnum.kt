package com.pscarpellini.enums

enum class ContaTipoEnum(val id: Int) {
    ADMIN(0),
    FUNCIONARIO(1),
    COLABORADOR(2);

    companion object {
        fun getTipo(name: String): ContaTipoEnum = entries.find { it.name == name } ?: COLABORADOR
        fun getTipo(id: Int): ContaTipoEnum = entries.find { it.id == id } ?: COLABORADOR
    }
}