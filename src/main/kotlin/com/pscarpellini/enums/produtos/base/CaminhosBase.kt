package com.pscarpellini.enums.produtos.base

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

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

    LOGOUT(path = "/logout"),
}