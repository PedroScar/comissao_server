package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.PosicoesDropdownEnum
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.includeMenuSeletorProduto(
    nome: String,
    classes: String = "",
) {
    div("flex flex-row items-center mb-4 $classes") {
        icone(IconesEnum.LUMEN, classes = "size-12")
        dropdown(
            posicao = PosicoesDropdownEnum.ESQUERDA,
            botao = {
                div(classes = "font-bold text-xl") {
                    +nome
                }
            },
        )
    }
}