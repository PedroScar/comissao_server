package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.frontend.fragments.geral.card.card
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
        tituloPagina.ifEmpty { +"Olá, ${sessao.nome}" }
    }
}