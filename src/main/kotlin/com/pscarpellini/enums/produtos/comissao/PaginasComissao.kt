package com.pscarpellini.enums.produtos.comissao

import com.pscarpellini.enums.PapeisDeAcessoEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class PaginasComissaoEnum(
    override val titulo: String,
    override val showBack: Boolean = false,
    override val sublinks: ArrayList<ISublinksRestritosEnum> = arrayListOf(),
    override val papelNecessario: PapeisDeAcessoEnum? = null,
    override val showBreadcrumbs: Boolean = false,
    override val breadcrumbs: ArrayList<IPaginaRestritaEnum> = arrayListOf(),
    override val caminho: IPaginaEnum,
) : IPaginaRestritaEnum {
    PROMOCOES(
        titulo = "Promoções",
        sublinks = arrayListOf(SublinksComissaoEnum.CRIAR_NOVA_PROMOCAO),
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
        caminho = CaminhosComissaoEnum.PROMOCOES
    ),
    NOVA_PROMOCAO(
        titulo = "Nova promoção",
        showBack = true,
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.NOVA_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        caminho = CaminhosComissaoEnum.NOVA_PROMOCAO
    ),
    EDITAR_PROMOCAO(
        titulo = "Exibir promoção",
        showBack = true,
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.EDITAR_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        caminho = CaminhosComissaoEnum.EDITAR_PROMOCAO
    ),

    SALDOS_DOS_PROMOTORES(
        titulo = "Saldos dos promotores",
        sublinks = arrayListOf(SublinksComissaoEnum.MODIFICAR_SALDO),
        caminho = CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES
    ),

    RELATORIOS(
        titulo = "Relatórios",
        sublinks = arrayListOf(),
        caminho = CaminhosComissaoEnum.RELATORIOS
    ),

    HISTORICO_DE_TRANSACOES(
        titulo = "Histórico de transações",
        caminho = CaminhosComissaoEnum.HISTORICO_DE_TRANSACOES
    ),
}