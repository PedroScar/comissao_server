package com.pscarpellini.models.vos

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.CategoriasMenuEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SessaoUsuarioVO(
    var paginaAtual: IPaginaRestritaEnum = PaginasRestritasEnum.INICIO
) {
    val menuSelecionado: ItensMenuEnum
        get() = paginaAtual.itemMenuSelecionado ?: ItensMenuEnum.INICIO

    var papeisDeAcesso: ArrayList<PapeisDeAcessoEnum> = arrayListOf()
    var servico = ServicoVO(
        id = 0,
        nome = "Comissão",
        dataCriacao = LocalDateTime.now()
    )
    var conta: ContaVO? = null

    var menusDisponiveis: ArrayList<IItensMenuEnum> = arrayListOf(
        ItensMenuEnum.INICIO,
        ItensMenuEnum.PROMOCOES,
        ItensMenuEnum.VIDEOS,
        ItensMenuEnum.SALDOS_DOS_PROMOTORES,
     //   ItensMenuEnum.RELATORIOS,
        CategoriasMenuEnum.ADMINISTRACAO
    )
}
