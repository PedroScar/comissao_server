package com.pscarpellini.frontend.enums

enum class ItensMenuPilulaEnum(
    val textoCTA: String,
    val icone: IconesEnum,
    val itemMenu: ItensMenuEnum,
) {
    CRIAR_NOVA_PROMOCAO(
        textoCTA = "Criar nova promoção",
        icone = IconesEnum.CARRINHO,
        itemMenu = ItensMenuEnum.PROMOCOES
    ),
    MODIFICAR_SALDO(
        textoCTA = "Modificar saldo",
        icone = IconesEnum.PRECO,
        itemMenu = ItensMenuEnum.SALDOS_DOS_PROMOTORES
    ),
    CADASTRAR_NOVO_USUARIO(
        textoCTA = "Cadastrar novo usuário",
        icone = IconesEnum.USUARIOS,
        itemMenu = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
    ),
    CONFIGURACOES_DO_APP(
        textoCTA = "Configurações do app",
        icone = IconesEnum.APP,
        itemMenu = ItensMenuEnum.CONFIGURACOES_DO_APP
    ),
}