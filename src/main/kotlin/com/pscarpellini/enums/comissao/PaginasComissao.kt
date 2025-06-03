package com.pscarpellini.enums.comissao

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
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
    override val itemMenuSelecionado: ItensMenuEnum?,
    override val caminho: IPaginaEnum,
) : IPaginaRestritaEnum {
    PROMOCOES(
        titulo = "Promoções",
        sublinks = arrayListOf(SublinksComissaoEnum.CRIAR_NOVA_PROMOCAO),
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
        caminho = CaminhosComissaoEnum.PROMOCOES,
        itemMenuSelecionado = ItensMenuEnum.PROMOCOES
    ),
    VIDEOS(
        titulo = "Videos",
        sublinks = arrayListOf(SublinksComissaoEnum.CRIAR_NOVA_PROMOCAO),
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_VIDEOS,
        caminho = CaminhosComissaoEnum.VIDEOS,
        itemMenuSelecionado = ItensMenuEnum.VIDEOS
    ),
    NOVA_PROMOCAO(
        titulo = "Nova promoção",
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.NOVA_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        caminho = CaminhosComissaoEnum.NOVA_PROMOCAO,
        itemMenuSelecionado = ItensMenuEnum.PROMOCOES
    ),
    EXIBIR_PROMOCAO(
        titulo = "Exibir promoção",
        papelNecessario = PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        caminho = CaminhosComissaoEnum.EXIBIR_PROMOCAO,
        itemMenuSelecionado = ItensMenuEnum.PROMOCOES
    ),
    EDITAR_PROMOCAO(
        titulo = "Exibir promoção",
        sublinks = arrayListOf(),
        papelNecessario = PapeisDeAcessoEnum.EDITAR_PROMOCAO,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(PROMOCOES),
        caminho = CaminhosComissaoEnum.EDITAR_PROMOCAO,
        itemMenuSelecionado = ItensMenuEnum.PROMOCOES
    ),

    SALDOS_DOS_PROMOTORES(
        titulo = "Saldos dos promotores",
        sublinks = arrayListOf(SublinksComissaoEnum.MODIFICAR_SALDO),
        caminho = CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES,
        itemMenuSelecionado = ItensMenuEnum.SALDOS_DOS_PROMOTORES
    ),

    RELATORIOS(
        titulo = "Relatórios",
        sublinks = arrayListOf(),
        caminho = CaminhosComissaoEnum.RELATORIOS,
        itemMenuSelecionado = ItensMenuEnum.RELATORIOS
    ),

    HISTORICO_DE_TRANSACOES(
        titulo = "Histórico de transações",
        caminho = CaminhosComissaoEnum.HISTORICO_DE_TRANSACOES,
        itemMenuSelecionado = ItensMenuEnum.HISTORICO_DE_TRANSACOES
    ),
}