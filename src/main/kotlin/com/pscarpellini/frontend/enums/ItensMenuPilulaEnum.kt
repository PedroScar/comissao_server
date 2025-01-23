package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IPaginaEnum

enum class ItensMenuPilulaEnum(
    val textoCTA: String,
    val icone: IconesEnum,
    val pagina: IPaginaEnum,
) {
    CRIAR_NOVA_PROMOCAO(
        textoCTA = "Criar nova promoção",
        icone = IconesEnum.CARRINHO,
        pagina = CaminhosComissaoEnum.NOVA_PROMOCAO
    ),
    MODIFICAR_SALDO(
        textoCTA = "Modificar saldo",
        icone = IconesEnum.PRECO,
        pagina = CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES
    ),
    CADASTRAR_NOVO_USUARIO(
        textoCTA = "Cadastrar novo usuário",
        icone = IconesEnum.USUARIOS,
        pagina = CaminhosBaseEnum.NOVO_USUARIO
    ),
    CONFIGURACOES_DO_APP(
        textoCTA = "Configurações do app",
        icone = IconesEnum.APP,
        pagina = CaminhosBaseEnum.CONFIGURACOES_DO_APP
    ),
}