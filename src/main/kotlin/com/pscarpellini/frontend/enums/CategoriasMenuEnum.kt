package com.pscarpellini.frontend.enums

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import com.pscarpellini.interfaces.IPaginaEnum

enum class CategoriasMenuEnum(
    override val nome: String,
    val itens: ArrayList<ItensMenuEnum>
): IItensMenuEnum {
    ADMINISTRACAO(
        nome = "administração",
        itens = arrayListOf(
            ItensMenuEnum.GERENCIAMENTO_DE_USUARIOS,
            ItensMenuEnum.CONFIGURACOES_DO_APP,
            ItensMenuEnum.HISTORICO_DE_TRANSACOES,
        )
    )
}