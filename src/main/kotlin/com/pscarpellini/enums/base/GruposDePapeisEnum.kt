package com.pscarpellini.enums.base

enum class GruposDePapeisEnum(
    val nome: String,
    val descricao: String,
    val papeis: ArrayList<PapeisDeAcessoEnum> = arrayListOf()
) {
    GERENCIAMENTO_DE_USUARIOS(
        nome = "Gerenciamento de usuários",
        descricao = "Papéis para gerenciar usuários",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.VISUALIZAR_USUARIOS,
            PapeisDeAcessoEnum.EDITAR_USUARIO,
            PapeisDeAcessoEnum.CRIAR_USUARIO,
            PapeisDeAcessoEnum.REENVIAR_SENHA,
            PapeisDeAcessoEnum.DESATIVAR_USUARIO,
        )
    ),
    GERENCIAMENTO_DE_SALDOS(
        nome = "Gerenciamento de saldos",
        descricao = "Papéis para gerenciar os saldos dos promotores",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.VISUALIZAR_SALDOS,
            PapeisDeAcessoEnum.EDITAR_SALDOS,
        )
    ),
    GERENCIAMENTO_DE_PROMOCOES(
        nome = "Gerenciamento de promoções",
        descricao = "Papéis para gerenciar promoções",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
            PapeisDeAcessoEnum.NOVA_PROMOCAO,
            PapeisDeAcessoEnum.EDITAR_PROMOCAO,
        )
    )
}