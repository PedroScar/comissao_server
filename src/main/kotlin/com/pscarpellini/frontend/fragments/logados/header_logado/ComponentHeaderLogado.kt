package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.includeHeaderLogado(
    sessao: SessaoUsuarioVO
) {
    div(classes = "w-full h-20 flex items-center bg-red-300") {
        +"Olá, ${sessao.nome}"
    }
}