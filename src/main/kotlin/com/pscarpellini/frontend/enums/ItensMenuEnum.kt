package com.pscarpellini.frontend.enums

enum class ItensMenuEnum(
    val nome: String,
    val icone: IconesEnum? = null,
    val tipo: TiposItensMenuEnum = TiposItensMenuEnum.ITEM,
    val caminho: String = "",
) {
    INICIO(
        nome = "Início",
        icone = IconesEnum.HOME,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/inicio",
    ),
    PROMOCOES(
        nome = "Promoções",
        icone = IconesEnum.CARRINHO,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/promocoes",
    ),
    SALDOS_DOS_PROMOTORES(
        nome = "Saldos dos promotores",
        icone = IconesEnum.PRECO,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/saldos",
    ),
    RELATORIOS(
        nome = "Relatórios",
        icone = IconesEnum.DOCUMENTO,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/relatorios",
    ),

    ADMINISTRACAO(nome = "administração", tipo = TiposItensMenuEnum.CATEGORIA),

    GERENCIAMENTO_DE_USUARIOS(
        nome = "Gerenciamento de usuários",
        icone = IconesEnum.USUARIOS,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/usuarios",
    ),
    CONFIGURACOES_DO_APP(
        nome = "Configurações do app",
        icone = IconesEnum.APP,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/configuracoes",
    ),
    HISTORICO_DE_TRANSACOES(
        nome = "Histórico de transações",
        icone = IconesEnum.HISTORICO,
        tipo = TiposItensMenuEnum.ITEM,
        caminho = "/int/historico",
    ),
}