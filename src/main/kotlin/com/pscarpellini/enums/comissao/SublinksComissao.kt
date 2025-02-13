package com.pscarpellini.enums.comissao

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.ICaminho
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum
import com.pscarpellini.rotas.FragmentsRestritosEnum

enum class SublinksComissaoEnum(
    override val icone: IconesEnum,
    override val nome: String,
    override val caminho: ICaminho,
    override val papelNecessario: PapeisDeAcessoEnum? = null,
) : ISublinksRestritosEnum {
    CRIAR_NOVA_PROMOCAO(
        nome = "Criar nova promoção",
        caminho = CaminhosComissaoEnum.NOVA_PROMOCAO,
        papelNecessario = PapeisDeAcessoEnum.NOVA_PROMOCAO,
        icone = IconesEnum.ADICIONAR
    ),
    MODIFICAR_SALDO(
        nome = "Modificar saldo",
        caminho = FragmentsRestritosEnum.FRAGMENT_POPUP_MODIFICAR_SALDO,
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_SALDOS,
        icone = IconesEnum.PRECO
    )
}