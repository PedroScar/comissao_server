package com.pscarpellini.frontend.enums

import com.pscarpellini.rotas.PaginasRestritasEnum

enum class ItensMenuPilulaEnum(
    val textoCTA: String,
    val icone: IconesEnum,
    val caminho: String,
) {
    CRIAR_NOVA_PROMOCAO(
        textoCTA = "Criar nova promoção",
        icone = IconesEnum.CARRINHO,
        caminho = ItensMenuEnum.PROMOCOES.caminho
    ),
    MODIFICAR_SALDO(
        textoCTA = "Modificar saldo",
        icone = IconesEnum.PRECO,
        caminho = ItensMenuEnum.SALDOS_DOS_PROMOTORES.caminho
    ),
    CADASTRAR_NOVO_USUARIO(
        textoCTA = "Cadastrar novo usuário",
        icone = IconesEnum.USUARIOS,
        caminho = PaginasRestritasEnum.NOVO_USUARIO.path
    ),
    CONFIGURACOES_DO_APP(
        textoCTA = "Configurações do app",
        icone = IconesEnum.APP,
        caminho = ItensMenuEnum.CONFIGURACOES_DO_APP.caminho
    ),
}