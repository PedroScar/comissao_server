package com.pscarpellini.frontend.fragments.geral.logo

import com.pscarpellini.frontend.enums.designsystem.TiposLogosEnum
import kotlinx.html.*

fun FlowContent.includeLogoLumen(
    tipo: TiposLogosEnum,
    tipoOnMobile: TiposLogosEnum = tipo,
    classes: String = "",
    clicavel: Boolean = true,
    destinoClique: String = "https://www.lumenapps.com.br/"
) {
    a(
        classes = if(!clicavel) "pointer-events-none" else "",
        href = destinoClique,
    ) {
        img(classes = "hidden lg:block $classes", src = tipo.caminho, alt = "Lumen Apps")
        img(classes = "lg:hidden lg:w-auto lg:h-auto $classes", src = tipoOnMobile.caminho, alt = "Lumen Apps")
    }
}