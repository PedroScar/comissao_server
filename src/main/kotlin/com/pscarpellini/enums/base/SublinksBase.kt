package com.pscarpellini.enums.base

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class SublinksBaseEnum(
    override val icone: IconesEnum,
    override val nome: String,
    override val caminho: IPaginaEnum,
    override val papelNecessario: PapeisDeAcessoEnum? = null,
) : ISublinksRestritosEnum {
    NOVO_USUARIO(
        nome = "Novo usuário",
        caminho = CaminhosBaseEnum.NOVO_USUARIO,
        papelNecessario = PapeisDeAcessoEnum.CRIAR_USUARIO,
        icone = IconesEnum.ADICIONAR
    ),
}