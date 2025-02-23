package com.pscarpellini.enums.base

import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class PaginasRestritasEnum(
    override val titulo: String,
    override val showBack: Boolean = false,
    override val sublinks: ArrayList<ISublinksRestritosEnum> = arrayListOf(),
    override val papelNecessario: PapeisDeAcessoEnum? = null,
    override val showBreadcrumbs: Boolean = false,
    override val breadcrumbs: ArrayList<IPaginaRestritaEnum> = arrayListOf(),
    override val itemMenuSelecionado: ItensMenuEnum? = null,
    override val caminho: IPaginaEnum,
) : IPaginaRestritaEnum {
    INTERNO(titulo = "", caminho = CaminhosBaseEnum.INTERNO),
    INICIO(titulo = "Olá!", caminho = CaminhosBaseEnum.INICIO),

    GERENCIAMENTO_DE_USUARIOS(
        titulo = "Gerenciamento de usuários",
        sublinks = arrayListOf(SublinksBaseEnum.NOVO_USUARIO),
        caminho = CaminhosBaseEnum.GERENCIAMENTO_DE_USUARIOS,
        itemMenuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
    ),
    NOVO_USUARIO(
        titulo = "Novo usuário",
        showBack = true,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(GERENCIAMENTO_DE_USUARIOS),
        caminho = CaminhosBaseEnum.NOVO_USUARIO,
        itemMenuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
    ),

    FORMULARIO_NOVO_USUARIO(
        titulo = "Novo usuário",
        caminho = CaminhosBaseEnum.FORMULARIO_NOVO_USUARIO,
        itemMenuSelecionado = ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS
    ),

    CONFIGURACOES_DO_APP(
        titulo = "Configurações do app",
        caminho = CaminhosBaseEnum.CONFIGURACOES_DO_APP,
        itemMenuSelecionado = ItensMenuEnum.CONFIGURACOES_DO_APP
    ),

    MEU_PERFIL(
        titulo = "Meu perfil",
        showBack = true,
        caminho = CaminhosBaseEnum.MEU_PERFIL,
        itemMenuSelecionado = ItensMenuEnum.INICIO
    ),
    EDITAR_MEU_PERFIL(
        titulo = "Editar meu perfil",
        showBack = true,
        caminho = CaminhosBaseEnum.EDITAR_MEU_PERFIL
    ),

    LOGOUT(titulo = "", caminho = CaminhosBaseEnum.LOGOUT),
}