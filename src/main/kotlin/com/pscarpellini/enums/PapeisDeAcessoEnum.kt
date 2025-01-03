package com.pscarpellini.enums

enum class PapeisDeAcessoEnum(
    val nome: String,
    val slug: String
) {
    VISUALIZAR_USUARIOS(nome = "Visualizar usuários", slug = "visualizar_usuario"),
    EDITAR_USUARIO(nome = "Editar usuário", slug = "editar_usuario"),
    CRIAR_USUARIO(nome = "Criar usuário", slug = "criar_usuario"),
    REENVIAR_SENHA(nome = "Reenviar senha", slug = "reenviar_senha"),
    DESATIVAR_USUARIO(nome = "Desativar usuário", slug = "desativar_usuario"),

}