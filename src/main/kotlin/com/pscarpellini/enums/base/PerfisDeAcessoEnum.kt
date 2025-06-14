package com.pscarpellini.enums.base

enum class PerfisDeAcessoEnum(
    val slug: String,
    val nome: String,
    val descricao: String,
    val papeis: ArrayList<PapeisDeAcessoEnum> = arrayListOf()
) {
    FUNCIONARIO(
        slug = "funcionario",
        nome = "Funcionário",
        descricao = "Funcionário: gerencia promoções e saldos dos promotores com acesso limitado a relatórios",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
            PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
            PapeisDeAcessoEnum.CRIAR_PROMOCAO,
            PapeisDeAcessoEnum.EDITAR_PROMOCAO,
            PapeisDeAcessoEnum.VISUALIZAR_VIDEOS,
            PapeisDeAcessoEnum.CRIAR_VIDEO,
            PapeisDeAcessoEnum.EDITAR_VIDEO,
        )
    ),
    PROMOTOR(
        slug = "promotor",
        nome = "Promotor",
        descricao = "Promotor: usa o app para visualizar saldo, extrato e promoções disponíveis para clientes",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
        )
    ),
    ADMINISTRADOR(
        slug = "administrador",
        nome = "Administrador",
        descricao = "Administrador: acesso total ao sistema, gerenciamento de promoções, usuários e relatórios",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,

            PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
            PapeisDeAcessoEnum.VISUALIZAR_VIDEOS,
            PapeisDeAcessoEnum.VISUALIZAR_USUARIOS,
            PapeisDeAcessoEnum.VISUALIZAR_SALDOS,
            PapeisDeAcessoEnum.VISUALIZAR_RELATORIOS,
            PapeisDeAcessoEnum.VISUALIZAR_CONFIGURACOES_DO_APP,
            PapeisDeAcessoEnum.VISUALIZAR_HISTORICO_DE_TRANSACOES,

            PapeisDeAcessoEnum.CRIAR_PROMOCAO,
            PapeisDeAcessoEnum.CRIAR_VIDEO,
            PapeisDeAcessoEnum.CRIAR_USUARIO,

            PapeisDeAcessoEnum.EDITAR_VIDEO,
            PapeisDeAcessoEnum.EDITAR_PROMOCAO,
            PapeisDeAcessoEnum.EDITAR_USUARIO,
            PapeisDeAcessoEnum.EDITAR_SALDOS,
            PapeisDeAcessoEnum.EDITAR_CONFIGURACOES_DO_APP,

            PapeisDeAcessoEnum.REENVIAR_SENHA,
            PapeisDeAcessoEnum.DESATIVAR_USUARIO,
        )
    ),
    PERSONALIZADO(
        slug = "personalizado",
        nome = "Personalizado",
        descricao = "Personalizado: Personalize as permissões de acesso para o usuário",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
        )
    );

    companion object {
        fun obterPerfisDisponiveis() = arrayListOf(FUNCIONARIO, PROMOTOR, ADMINISTRADOR)
        fun obterEnumPeloSlug(slug: String) = entries.firstOrNull { it.slug == slug } ?: FUNCIONARIO
    }
}