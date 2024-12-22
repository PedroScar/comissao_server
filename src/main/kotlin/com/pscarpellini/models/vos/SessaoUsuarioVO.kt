package com.pscarpellini.models.vos

import com.pscarpellini.frontend.enums.ItensMenuEnum
import kotlinx.serialization.Serializable

@Serializable
data class SessaoUsuarioVO(
    val email: String? = null,
    val nome: String? = null,
    val cliente: String? = null,
    val menuSelecionado: ItensMenuEnum = ItensMenuEnum.INICIO,
    val menusDisponiveis: ArrayList<ItensMenuEnum> = ArrayList(ItensMenuEnum.entries),
//    val tipoConta: TipoContaEnum,
//    val permissoes: List<PermissoesEnum> = arrayListOf(
//        ""
//    )
)