package com.pscarpellini.enums.produtos.base

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class SublinksBaseEnum(
    override val nome: String,
    override val path: String,
    override val icone: IconesEnum,
) : ISublinksRestritosEnum {
    NOVO_USUARIO(
        nome = "Novo usuário",
        path = "/int/novo_usuario",
        icone = IconesEnum.ADICIONAR
    ),
}