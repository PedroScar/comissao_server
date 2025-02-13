package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.toggleComTexto(
    classes: String = "",
    checked: Boolean = true,
    conteudoChecked: FlowContent.() -> Unit,
    conteudoUnchecked: FlowContent.() -> Unit,
    nomeDoCampo: String,
) {
    label(classes = "flex flex-row relative gap-2 cursor-pointer p-2 ${CoresEnum.ALERT_LIGHT.bg} has-[:checked]:${CoresEnum.SUCCESS_LIGHT.bg} ${ArredondamentosEnum.PILL} transition-all duration-300 $classes") {
        attributes["id"] = nomeDoCampo
        attributes["name"] = nomeDoCampo
        if(checked) attributes["checked"] = ""
        input(type = InputType.checkBox, classes = "hidden peer")
        span(classes = "z-10 transition-all duration-300 px-4 py-2 flex flex-row font-semibold items-center peer-checked:*:invert *:invert-0 dark:${CoresEnum.SUCCESS_DARK.bg} ${CoresEnum.TRANSPARENT.bg} peer-checked:${CoresEnum.SUCCESS_DARK.bg} ${CoresEnum.LOW_PURE.text} peer-checked:${CoresEnum.SUCCESS_LIGHT.text} ${ArredondamentosEnum.PILL}") {
            conteudoChecked()
        }
        span(classes = "z-10 transition-all duration-300 px-4 py-2 flex flex-row font-semibold items-center *:invert peer-checked:*:invert-0 dark:${CoresEnum.ALERT_DARK.bg} ${CoresEnum.ALERT_DARK.bg} peer-checked:${CoresEnum.TRANSPARENT.bg} ${CoresEnum.ALERT_LIGHT.text} peer-checked:${CoresEnum.LOW_PURE.text} ${ArredondamentosEnum.PILL}") {
            conteudoUnchecked()
        }
    }
}