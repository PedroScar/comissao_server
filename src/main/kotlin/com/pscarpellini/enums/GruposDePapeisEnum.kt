package com.pscarpellini.enums

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
    )
}