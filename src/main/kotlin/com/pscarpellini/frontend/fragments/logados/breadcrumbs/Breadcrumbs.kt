package com.pscarpellini.frontend.fragments.logados.breadcrumbs

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.fragments.geral.navigation.navigationHX
import com.pscarpellini.interfaces.IPaginaRestritaEnum
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.breadcrumbs(
    paginaAtual: IPaginaRestritaEnum,
) {
    div(classes = "flex gap-2") {
        paginaAtual.breadcrumbs.forEach { breadcrumb ->
            navigationHX(texto = breadcrumb.titulo, hxPath = breadcrumb.caminho, classes = CoresEnum.BRAND_DARK.text)
            +"/"
        }
        span { +paginaAtual.titulo }
    }
}