package com.pscarpellini.enums.base

enum class PapeisDeAcessoEnum(
    val nome: String,
    val slug: String
) {
    INICIO(nome = "Início", slug = "inicio"),

    VISUALIZAR_PROMOCOES(nome = "Visualizar promoções", slug = "visualizar_promocoes"),
    CRIAR_PROMOCAO(nome = "Nova promoção", slug = "nova_promocao"),
    EDITAR_PROMOCAO(nome = "Editar promoção", slug = "editar_promocao"),

    VISUALIZAR_VIDEOS(nome = "Visualizar videos", slug = "visualizar_videos"),
    CRIAR_VIDEO(nome = "Novo video", slug = "novo_video"),
    EDITAR_VIDEO(nome = "Editar video", slug = "editar_video"),

    VISUALIZAR_SALDOS(nome = "Visualizar saldos", slug = "visualizar_saldos"),
    EDITAR_SALDOS(nome = "Editar saldos", slug = "editar_saldos"),

    VISUALIZAR_RELATORIOS(nome = "Visualizar relatórios", slug = "visualizar_relatorios"),

    VISUALIZAR_USUARIOS(nome = "Visualizar usuários", slug = "visualizar_usuario"),
    EDITAR_USUARIO(nome = "Editar usuário", slug = "editar_usuario"),
    CRIAR_USUARIO(nome = "Criar usuário", slug = "criar_usuario"),
    REENVIAR_SENHA(nome = "Reenviar senha", slug = "reenviar_senha"),
    DESATIVAR_USUARIO(nome = "Desativar usuário", slug = "desativar_usuario"),

    VISUALIZAR_CONFIGURACOES_DO_APP(nome = "Visualizar configurações do app", slug = "visualizar_configuracoes_do_app"),
    EDITAR_CONFIGURACOES_DO_APP(nome = "Editar configurações do app", slug = "editar_configuracoes_do_app"),

    VISUALIZAR_HISTORICO_DE_TRANSACOES(nome = "Visualizar histórico de transações", slug = "visualizar_historico_de_transacoes"),
}