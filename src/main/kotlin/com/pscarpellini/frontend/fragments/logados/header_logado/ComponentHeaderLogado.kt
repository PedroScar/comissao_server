package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.includeHeaderLogado(
    sessao: SessaoUsuarioVO,
    tituloPagina: String = "",
) {
    card(
        showBackground = false,
        classes = "w-full flex items-center"
    ) {
        span(classes = "font-semibold text-xl") { tituloPagina.ifEmpty { +"Olá, ${sessao.nome}" } }
        spacer()
        a(href = "", classes = "hover:underline") {
            card(showBackground = false, usarPadding = false, classes = "mx-4") {
                icone(IconesEnum.AJUDA, size = 2)
                +"Central de ajuda"
            }
        }
        icone(IconesEnum.USUARIO, showBackground = true)
        icone(IconesEnum.CHEVRON_DOWN)
    }
}