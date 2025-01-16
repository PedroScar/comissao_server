package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import kotlinx.html.*

fun FlowContent.includeCardDePerfis(
    perfisDeAcesso: List<PerfilDeAcessoVO>?
) {
    div(classes = "${CoresEnum.BRAND_LIGHT.bg} ${ArredondamentosEnum.MD} py-4 px-6 w-full") {
        span { +"Tipos de contas:" }
        ul (classes = "list-disc ms-6") {
            perfisDeAcesso?.forEach {
                li {
                    b { +"${it.nome}: " }
                    +it.descricao
                }
            }
        }
    }
}