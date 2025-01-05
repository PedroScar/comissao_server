package com.pscarpellini.models.vos

import com.pscarpellini.frontend.enums.ItensMenuEnum
import kotlinx.serialization.Serializable

@Serializable
data class SessaoUsuarioVO(
    var menuSelecionado: ItensMenuEnum? = ItensMenuEnum.INICIO,
    var menusDisponiveis: ArrayList<ItensMenuEnum> = ArrayList(ItensMenuEnum.entries),
) {
    var conta: ContaVO? = null
}
