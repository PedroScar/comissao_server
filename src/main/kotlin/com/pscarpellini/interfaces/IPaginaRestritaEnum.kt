package com.pscarpellini.interfaces

import com.pscarpellini.enums.PapeisDeAcessoEnum

interface IPaginaRestritaEnum: IPaginaEnum {
    val titulo: String
    val showBack: Boolean
    val sublinks: ArrayList<ISublinksRestritosEnum>
    val papelNecessario: PapeisDeAcessoEnum?
    val showBreadcrumbs: Boolean
    val breadcrumbs: ArrayList<IPaginaRestritaEnum>
    override val path: String
}