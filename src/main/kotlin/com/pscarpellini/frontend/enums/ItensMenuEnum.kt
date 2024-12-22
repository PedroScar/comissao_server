package com.pscarpellini.frontend.enums

enum class ItensMenuEnum(
    val nome: String,
    val icone: IconesEnum? = null,
    val tipo: TiposItensMenuEnum = TiposItensMenuEnum.ITEM
) {
    INICIO(nome = "Início", icone = IconesEnum.HOME, tipo = TiposItensMenuEnum.ITEM),
    PROMOCOES(nome = "Promoções", icone = IconesEnum.CARRINHO, tipo = TiposItensMenuEnum.ITEM),
    SALDOS_DOS_PROMOTORES(nome = "Saldos dos promotores", icone = IconesEnum.PRECO, tipo = TiposItensMenuEnum.ITEM),
    RELATORIOS(nome = "Relatórios", icone = IconesEnum.DOCUMENTO, tipo = TiposItensMenuEnum.ITEM),
    ADMINISTRACAO(nome = "administração", tipo = TiposItensMenuEnum.CATEGORIA),
    GERENCIAMENTO_DE_USUARIOS(nome = "Gerenciamento de usuários", icone = IconesEnum.USUARIOS, tipo = TiposItensMenuEnum.ITEM),
    CONFIGURACOES_DO_APP(nome = "Configurações do app", icone = IconesEnum.APP, tipo = TiposItensMenuEnum.ITEM),
    HISTORICO_DE_TRANSACOES(nome = "Histórico de transações", icone = IconesEnum.RELOGIO, tipo = TiposItensMenuEnum.ITEM),
}