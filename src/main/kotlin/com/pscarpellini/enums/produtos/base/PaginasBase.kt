package com.pscarpellini.enums.produtos.base

import com.pscarpellini.enums.PapeisDeAcessoEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class PaginasRestritasEnum(
    override val titulo: String,
    override val showBack: Boolean = false,
    override val sublinks: ArrayList<ISublinksRestritosEnum> = arrayListOf(),
    override val papelNecessario: PapeisDeAcessoEnum? = null,
    override val showBreadcrumbs: Boolean = false,
    override val breadcrumbs: ArrayList<IPaginaRestritaEnum> = arrayListOf(),
    override val path: String
): IPaginaRestritaEnum {
    INTERNO(titulo = "", path = "/int/{path}"),

    INICIO(titulo = "Olá!", path = "/int/inicio"),

    GERENCIAMENTO_DE_USUARIOS(
        titulo = "Gerenciamento de usuários",
        sublinks = arrayListOf(SublinksBaseEnum.NOVO_USUARIO),
        path = "/int/usuarios"
    ),
    NOVO_USUARIO(
        titulo = "Novo usuário",
        showBack = true,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(GERENCIAMENTO_DE_USUARIOS),
        path = SublinksBaseEnum.NOVO_USUARIO.path
    ),

    CONFIGURACOES_DO_APP(
        titulo = "Configurações do app",
        path = "/int/configuracoes"
    ),

    MEU_PERFIL(
        titulo = "Meu perfil",
        showBack = true,
        showBreadcrumbs = true,
        breadcrumbs = arrayListOf(INICIO),
        path = "/int/meu_perfil"
    ),

    LOGOUT(titulo = "", path = "/logout"),
}