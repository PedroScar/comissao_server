package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun FlowContent.selectField(
    classes: String = "",
    enabled: Boolean = true,
    hint: String = "",
    opcoes: List<Pair<String, String>> = arrayListOf(),
    opcaoDefault: String? = null,
    label: String? = null,
    isObrigatorio: Boolean = false,
    hxMethod: FormMethod = FormMethod.post,
    hxOnChangePath: String? = null,
    hxTarget: String? = null,
    nomeDoCampo: String,
) {
    val opcoesVisiveis = arrayListOf<Pair<String, String>>()
    if(!isObrigatorio) opcoesVisiveis.add("" to hint)
    opcoesVisiveis.addAll(opcoes)
    div(classes = "flex flex-col $classes") {
        if(!label.isNullOrBlank()) {
            label(classes = "${if(enabled) CoresEnum.LOW_PURE.text else "${CoresEnum.LOW_LIGHT.text} pointer-events-none"} block text-base font-semibold") {
                htmlFor = nomeDoCampo
                +label
                if(isObrigatorio) span(classes = CoresEnum.ALERT_PURE.text) { +" *" }
            }
        }

        // Container do Input e Ícone
        div(classes = "relative mt-1") {
            // Campo de Input
            select (classes = "peer appearance-none ${if (enabled) "${CoresEnum.HIGH_LIGHT.bg} hover:${CoresEnum.HIGH_MEDIUM.bg} ${CoresEnum.LOW_PURE.text}" else "${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text} pointer-events-none"} ring-inset w-full px-4 py-2 font-semibold ${ArredondamentosEnum.MD} focus:ring-1 focus:ring-${CoresEnum.LOW_PURE}") {
                if(hxOnChangePath != null) attributes["hx-${hxMethod.name}"] = hxOnChangePath
                if(hxTarget != null) {
                    attributes["hx-trigger"] = "change,load"
                    attributes["hx-target"] = "#$hxTarget"
                }

                id = nomeDoCampo
                name = nomeDoCampo
                if (!enabled) attributes["disabled"] = "disabled"
                if (!isObrigatorio) attributes["required"] = "required"

                opcoesVisiveis.forEach { (value, text) ->
                    option {
                        this.value = value
                        if (opcaoDefault == value) attributes["selected"] = "selected"
                        +text
                    }
                }
            }
            div(classes = "pointer-events-none absolute inset-y-0 right-2 flex items-center peer-focus:rotate-180 transition-all duration-300") {
                icone(IconesEnum.CHEVRON_DOWN, size = 2f)
            }
        }
    }
}