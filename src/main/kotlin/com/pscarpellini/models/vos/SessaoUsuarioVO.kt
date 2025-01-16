package com.pscarpellini.models.vos

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.CategoriasMenuEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import kotlinx.serialization.Serializable

@Serializable
data class SessaoUsuarioVO(
    var paginaAtual: IPaginaRestritaEnum = PaginasRestritasEnum.INICIO,
    var menuSelecionado: ItensMenuEnum? = ItensMenuEnum.INICIO,
) {
    var conta: ContaVO? = null
    var menusDisponiveis: ArrayList<IItensMenuEnum> = arrayListOf(
        ItensMenuEnum.INICIO,
        ItensMenuEnum.PROMOCOES,
        ItensMenuEnum.SALDOS_DOS_PROMOTORES,
        ItensMenuEnum.RELATORIOS,
        CategoriasMenuEnum.ADMINISTRACAO
    )
}
