package com.pscarpellini.enums.produtos.comissao

import com.pscarpellini.enums.PapeisDeAcessoEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class PaginasComissaoEnum(
    override val titulo: String,
    override val showBack: Boolean = false,
    override val sublinks: ArrayList<ISublinksRestritosEnum> = arrayListOf(),
    override val papelNecessario: PapeisDeAcessoEnum? = null,
    override val showBreadcrumbs: Boolean = false,
    override val breadcrumbs: ArrayList<IPaginaRestritaEnum> = arrayListOf(),
    override val path: String
) : IPaginaRestritaEnum {
    PROMOCOES(
        titulo = "Promoções",
        sublinks = arrayListOf(SublinksComissaoEnum.CRIAR_NOVA_PROMOCAO),
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
        path = "/int/promocoes"
    ),
    NOVA_PROMOCAO(
        titulo = "Nova promoção",
        showBack = true,
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.NOVA_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        path = SublinksComissaoEnum.CRIAR_NOVA_PROMOCAO.path
    ),
    EDITAR_PROMOCAO(
        titulo = "Exibir promoção",
        showBack = true,
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.EDITAR_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        path = "/int/promocoes"
    ),

    SALDOS_DOS_PROMOTORES(
        titulo = "Saldos dos promotores",
        sublinks = arrayListOf(SublinksComissaoEnum.MODIFICAR_SALDO),
        path = SublinksComissaoEnum.MODIFICAR_SALDO.path
    ),

    RELATORIOS(
        titulo = "Relatórios",
        sublinks = arrayListOf(),
        path = "/int/relatorios"
    ),

    HISTORICO_DE_TRANSACOES(
        titulo = "Histórico de transações",
        path = "/int/historico"
    ),
}