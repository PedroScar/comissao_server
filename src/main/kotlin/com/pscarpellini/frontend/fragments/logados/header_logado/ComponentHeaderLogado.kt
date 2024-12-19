package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.backend.models.dto.UserSession
import kotlinx.html.*

fun FlowContent.includeHeaderLogado(
    sessao: UserSession
) {
    div(classes = "w-full h-20 flex items-center bg-red-300") {
        +"Olá, ${sessao.nome}"
    }
}