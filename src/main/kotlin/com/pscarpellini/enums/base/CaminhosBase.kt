package com.pscarpellini.enums.base

import com.pscarpellini.interfaces.IPaginaEnum

enum class CaminhosBaseEnum(
    override val path: String,
) : IPaginaEnum {
    INTERNO(path = "/int/{path}"),

    INICIO(path = "/int/inicio"),

    GERENCIAMENTO_DE_USUARIOS(path = "/int/usuarios"),
    NOVO_USUARIO(path = "/int/novo_usuario"),
    FORMULARIO_NOVO_USUARIO(path = "/forms/novo_usuario"),

    CONFIGURACOES_DO_APP(path = "/int/configuracoes"),

    MEU_PERFIL(path = "/int/meu_perfil"),
    EDITAR_MEU_PERFIL(path = "/int/meu_perfil/editar"),
    FORMULARIO_EDITAR_MEU_PERFIL(path = "/forms/meu_perfil/editar"),

    LOGOUT(path = "/logout"),
}