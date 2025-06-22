package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import kotlinx.html.*

fun FlowContent.inputFileUpload(
    tipo: TiposBotaoEnum = TiposBotaoEnum.PRIMARY,
    classes: String = "",
    interativo: Boolean = true,
    small: Boolean = false,
    nomeDoCampo: String,
    enabled: Boolean = true,
    conteudo: FlowContent.() -> Unit,
) {
    div(classes = "relative inline-block") {
        button(classes = "${tipo.cssProprio} disabled:pointer-events-none select-none font-semibold ${if (small) "py-1 px-4" else "py-2 px-6"} ${if (interativo) "cursor-pointer" else ""} transition-all duration-300 ${if (!enabled) "text-low-light" else ""} $classes") {
            conteudo()
        }
        input(
            type = InputType.file,
            classes = "absolute inset-0 w-full h-full opacity-0 cursor-pointer"
        ) {
            id = nomeDoCampo
            name = nomeDoCampo
        }
    }
}
