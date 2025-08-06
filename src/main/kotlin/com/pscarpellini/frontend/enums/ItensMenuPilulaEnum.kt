package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.ICaminho
import com.pscarpellini.rotas.FragmentsRestritosEnum

enum class ItensMenuPilulaEnum(
    val textoCTA: String,
    val icone: IconesEnum,
    val pagina: ICaminho,
    val usarReplace: Boolean = true
) {
    CRIAR_NOVA_PROMOCAO(
        textoCTA = "Criar promoção",
        icone = IconesEnum.CARRINHO,
        pagina = CaminhosComissaoEnum.CRIAR_PROMOCAO
    ),
    CRIAR_NOVO_VIDEO(
        textoCTA = "Criar video",
        icone = IconesEnum.VIDEOS,
        pagina = CaminhosComissaoEnum.CRIAR_VIDEO
    ),
    MODIFICAR_SALDO(
        textoCTA = "Modificar saldo",
        icone = IconesEnum.PRECO,
        pagina = FragmentsRestritosEnum.FRAGMENT_POPUP_MODIFICAR_SALDO,
        usarReplace = false
    ),
    CADASTRAR_NOVO_USUARIO(
        textoCTA = "Cadastrar usuário",
        icone = IconesEnum.USUARIOS,
        pagina = CaminhosBaseEnum.USUARIO_NOVO
    ),
    CONFIGURACOES_DO_APP(
        textoCTA = "Configurar app",
        icone = IconesEnum.APP,
        pagina = CaminhosBaseEnum.EXIBIR_CONFIGURACOES_DO_APP
    ),
}