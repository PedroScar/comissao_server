package com.pscarpellini.enums.produtos.comissao

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class SublinksComissaoEnum(
    override val nome: String,
    override val path: String,
    override val icone: IconesEnum,
) : ISublinksRestritosEnum {
    CRIAR_NOVA_PROMOCAO(
        nome = "Criar nova promoção",
        path = "/int/promocoes/nova",
        icone = IconesEnum.ADICIONAR
    ),
    MODIFICAR_SALDO(
        nome = "Modificar saldo",
        path = "/int/saldos",
        icone = IconesEnum.PRECO
    )
}