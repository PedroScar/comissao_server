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
        descricao = "gerencia promoções e saldos dos promotores com acesso limitado a relatórios",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
            PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
            PapeisDeAcessoEnum.CRIAR_PROMOCAO,
            PapeisDeAcessoEnum.EDITAR_PROMOCAO,
            PapeisDeAcessoEnum.VISUALIZAR_VIDEOS,
            PapeisDeAcessoEnum.CRIAR_VIDEO,
            PapeisDeAcessoEnum.EDITAR_VIDEO,
            PapeisDeAcessoEnum.REMOVER_VIDEO,
        )
    ),
    PROMOTOR(
        slug = "promotor",
        nome = "Promotor",
        descricao = "usa o app para visualizar saldo, extrato e promoções disponíveis para clientes",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
        )
    ),
    ADMINISTRADOR(
        slug = "administrador",
        nome = "Administrador",
        descricao = "acesso total ao sistema, gerenciamento de promoções, usuários e relatórios",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,

            PapeisDeAcessoEnum.CRIAR_PROMOCAO,
            PapeisDeAcessoEnum.EDITAR_PROMOCAO,
            PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
            PapeisDeAcessoEnum.REMOVER_PROMOCAO,

      //      PapeisDeAcessoEnum.VISUALIZAR_RELATORIOS, //TODO Criar tela de relatórios

            PapeisDeAcessoEnum.CRIAR_VIDEO,
            PapeisDeAcessoEnum.EDITAR_VIDEO,
            PapeisDeAcessoEnum.REMOVER_VIDEO,
            PapeisDeAcessoEnum.VISUALIZAR_VIDEOS,

            PapeisDeAcessoEnum.CRIAR_USUARIO,
            PapeisDeAcessoEnum.EDITAR_USUARIO,
            PapeisDeAcessoEnum.DESATIVAR_USUARIO,
            PapeisDeAcessoEnum.VISUALIZAR_USUARIOS,

            PapeisDeAcessoEnum.EDITAR_SALDOS,
            PapeisDeAcessoEnum.VISUALIZAR_SALDOS,

            PapeisDeAcessoEnum.EDITAR_CONFIGURACOES_DO_APP,
            PapeisDeAcessoEnum.REENVIAR_SENHA,
            PapeisDeAcessoEnum.VISUALIZAR_CONFIGURACOES_DO_APP,
            PapeisDeAcessoEnum.VISUALIZAR_HISTORICO_DE_TRANSACOES,
        )
    ),
    PERSONALIZADO(
        slug = "personalizado",
        nome = "Personalizado",
        descricao = "Personalize as permissões de acesso para o usuário",
        papeis = arrayListOf(
            PapeisDeAcessoEnum.INICIO,
        )
    );

    companion object {
        fun obterPerfisDisponiveis() = arrayListOf(FUNCIONARIO, PROMOTOR, ADMINISTRADOR)
        fun obterEnumPeloSlug(slug: String) = entries.firstOrNull { it.slug.equals(slug, ignoreCase = true) } ?: FUNCIONARIO
    }
}