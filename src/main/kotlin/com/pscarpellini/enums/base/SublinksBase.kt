package com.pscarpellini.enums.base

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.ICaminho
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class SublinksBaseEnum(
    override val icone: IconesEnum,
    override val nome: String,
    override val caminho: ICaminho,
    override val papelNecessario: PapeisDeAcessoEnum? = null,
) : ISublinksRestritosEnum {
    NOVO_USUARIO(
        nome = "Novo usuário",
        caminho = CaminhosBaseEnum.USUARIO_NOVO,
        papelNecessario = PapeisDeAcessoEnum.CRIAR_USUARIO,
        icone = IconesEnum.ADICIONAR
    ),
}