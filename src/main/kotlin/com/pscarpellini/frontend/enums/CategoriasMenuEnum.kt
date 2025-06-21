package com.pscarpellini.frontend.enums

import com.pscarpellini.interfaces.IItensMenuEnum

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