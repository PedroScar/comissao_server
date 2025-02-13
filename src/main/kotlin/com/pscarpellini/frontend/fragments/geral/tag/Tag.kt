package com.pscarpellini.frontend.fragments.geral.tag

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.rotas.FragmentsComponentesEnum
import kotlinx.html.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun FlowContent.tag(
    tipo: TiposTagsEnum = TiposTagsEnum.NEUTRAL,
    nome: String = System.currentTimeMillis().toString(),
    clicavel: Boolean = false,
    classes: String = "",
    texto: String,
    isSecundaria: Boolean = false,
    exibirX: Boolean = true,
    isSelected: Boolean = false
) {
    val idDoConteudo = "$nome-${System.currentTimeMillis()}"
    button(
        classes = "${if (isSecundaria) tipo.cssSecundaria else tipo.cssProprio} disabled:pointer-events-none select-none font-semibold py-2 px-2 m-1 shrink ${if (clicavel) "cursor-pointer" else "cursor-default"} transition-all duration-300 flex flex-row items-center $classes",
    ) {
        if (clicavel) {
            attributes["hx-post"] = FragmentsComponentesEnum.COMPONENTE_TAG_TOGGGLE.path
            attributes["hx-swap"] = "outerHTML"
            attributes["id"] = idDoConteudo
            attributes["name"] = nome
            with(
                mapOf(
                    "tipo" to tipo.name,
                    "nome" to nome,
                    "classes" to classes,
                    "texto" to texto,
                    "exibirX" to exibirX.toString(),
                    "isSelected" to isSelected.toString(),
                )
            ) { attributes["hx-vals"] = Json.encodeToString(this) }
        }
        if (isSelected && clicavel) icone(icone = IconesEnum.CHECK, usarPadding = false)
        span(classes = "mx-2") { +texto }
        if (exibirX && clicavel) icone(icone = IconesEnum.CLOSE, usarPadding = false)
    }
}