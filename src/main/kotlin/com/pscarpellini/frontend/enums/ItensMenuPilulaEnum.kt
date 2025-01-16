package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.enums.produtos.comissao.PaginasComissaoEnum

enum class ItensMenuPilulaEnum(
    val textoCTA: String,
    val icone: IconesEnum,
    val pagina: IPaginaEnum,
) {
    CRIAR_NOVA_PROMOCAO(
        textoCTA = "Criar nova promoção",
        icone = IconesEnum.CARRINHO,
        pagina = PaginasComissaoEnum.PROMOCOES
    ),
    MODIFICAR_SALDO(
        textoCTA = "Modificar saldo",
        icone = IconesEnum.PRECO,
        pagina = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES
    ),
    CADASTRAR_NOVO_USUARIO(
        textoCTA = "Cadastrar novo usuário",
        icone = IconesEnum.USUARIOS,
        pagina = PaginasRestritasEnum.NOVO_USUARIO
    ),
    CONFIGURACOES_DO_APP(
        textoCTA = "Configurações do app",
        icone = IconesEnum.APP,
        pagina = PaginasRestritasEnum.CONFIGURACOES_DO_APP
    ),
}