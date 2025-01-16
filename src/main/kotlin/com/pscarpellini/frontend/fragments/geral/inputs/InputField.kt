package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.inputField(
    classes: String = "",
    enabled: Boolean = true,
    inputType: InputType,
    hint: String = "",
    label: String? = null,
    isObrigatorio: Boolean = false,
    nomeDoCampo: String,
    icone: IconesEnum? = null,
    useHx: Boolean = false,
    hxTrigger: String = "keyup changed delay:1s",
    hxPost: String = "",
    hxTarget: String = "",
    onIconClick: (() -> Unit)? = null,
) {
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
            input(classes = "${if (enabled) "${CoresEnum.HIGH_LIGHT.bg} hover:${CoresEnum.HIGH_MEDIUM.bg} ${CoresEnum.LOW_PURE.text}" else "${CoresEnum.HIGH_DARK.bg} ${CoresEnum.LOW_LIGHT.text} pointer-events-none"} ring-inset w-full px-4 py-2 font-semibold ${ArredondamentosEnum.MD} focus:ring-1 focus:ring-${CoresEnum.LOW_PURE}") {
                type = inputType
                id = nomeDoCampo
                name = nomeDoCampo
                placeholder = hint

                if(useHx) {
                    attributes["hx-trigger"] = hxTrigger
                    attributes["hx-post"] = hxPost
                    attributes["hx-target"] = "#$hxTarget"
                }

                if (!enabled) attributes["disabled"] = "disabled"
                if (isObrigatorio) attributes["required"] = "required"
            }
            // Ícone no Lado Direito
            if (icone != null) {
                div(classes = "absolute inset-y-0 right-0 flex items-center pr-3 cursor-pointer") {
                    onIconClick?.let { onClick -> attributes["onClick"] = "(${onClick::class.simpleName})()" }
                    icone(icone, usarPadding = false)
                }
            }
        }
    }
}