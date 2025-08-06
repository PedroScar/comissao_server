package com.pscarpellini.enums.base

import com.pscarpellini.interfaces.IPaginaEnum

enum class CaminhosBaseEnum(
    override val path: String,
) : IPaginaEnum {
    INTERNO(path = "/int/{path}"),

    INICIO(path = "/int/inicio"),

    GERENCIAMENTO_DE_USUARIOS(path = "/int/usuarios"),
    USUARIO_NOVO(path = "/int/usuario_novo"),
    USUARIO_EXIBIR(path = "/int/usuario_exibir"),
    USUARIO_EDITAR(path = "/int/usuario_editar"),
    FORMULARIO_NOVO_USUARIO(path = "/forms/novo_usuario"),
    FORMULARIO_EDITAR_USUARIO(path = "/forms/editar_usuario"),

    EXIBIR_CONFIGURACOES_DO_APP(path = "/int/configuracoes"),
    EDITAR_CONFIGURACOES_DO_APP(path = "/int/configuracoes/editar"),
    FORMULARIO_EDITAR_CONFIGURACOES_APP(path = "/forms/configuracoes/editar"),

    MEU_PERFIL(path = "/int/meu_perfil"),
    EDITAR_MEU_PERFIL(path = "/int/meu_perfil/editar"),
    FORMULARIO_EDITAR_MEU_PERFIL(path = "/forms/meu_perfil/editar"),
    ALTERAR_SENHA_PERFIL(path = "/int/meu_perfil/alterar_senha"),
    FORMULARIO_ALTERAR_SENHA_PERFIL(path = "/forms/meu_perfil/alterar_senha"),

    LOGOUT(path = "/logout"),
}