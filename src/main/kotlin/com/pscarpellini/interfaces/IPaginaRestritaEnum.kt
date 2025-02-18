package com.pscarpellini.interfaces

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum

interface IPaginaRestritaEnum {
    val titulo: String
    val showBack: Boolean
    val sublinks: ArrayList<ISublinksRestritosEnum>
    val papelNecessario: PapeisDeAcessoEnum?
    val showBreadcrumbs: Boolean
    val breadcrumbs: ArrayList<IPaginaRestritaEnum>
    val itemMenuSelecionado: ItensMenuEnum?
    val caminho: IPaginaEnum
}