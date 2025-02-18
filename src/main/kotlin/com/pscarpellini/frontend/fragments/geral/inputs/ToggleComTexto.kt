package com.pscarpellini.frontend.fragments.geral.inputs

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.toggleComTexto(
    classes: String = "",
    checked: Boolean = true,
    conteudoEsquerdaChecked: FlowContent.() -> Unit,
    conteudoEsquerdaUnchecked: FlowContent.() -> Unit,
    conteudoDireitaUnchecked: FlowContent.() -> Unit,
    conteudoDireitaChecked: FlowContent.() -> Unit,
    nomeDoCampo: String,
) {
    label(classes = "flex flex-row relative gap-2 cursor-pointer p-2 ${CoresEnum.ALERT_LIGHT.bg} has-[:checked]:${CoresEnum.SUCCESS_LIGHT.bg} ${ArredondamentosEnum.PILL} transition-all duration-300 $classes") {
        htmlFor = nomeDoCampo
        input(type = InputType.checkBox, classes = "hidden peer") {
            attributes["id"] = nomeDoCampo
            attributes["name"] = nomeDoCampo
            attributes["value"] = "true"
            if(checked) attributes["checked"] = ""
        }
        span(classes = "z-10 transition-all duration-300 px-4 py-2 hidden peer-checked:flex flex-row font-semibold items-center ${CoresEnum.TRANSPARENT.bg} peer-checked:${CoresEnum.SUCCESS_DARK.bg} ${CoresEnum.LOW_PURE.text} peer-checked:${CoresEnum.SUCCESS_LIGHT.text} ${ArredondamentosEnum.PILL}") {
            conteudoEsquerdaChecked()
        }
        span(classes = "z-10 transition-all duration-300 px-4 py-2 peer-checked:hidden flex flex-row font-semibold items-center ${CoresEnum.TRANSPARENT.bg} peer-checked:${CoresEnum.SUCCESS_DARK.bg} ${CoresEnum.LOW_PURE.text} peer-checked:${CoresEnum.SUCCESS_LIGHT.text} ${ArredondamentosEnum.PILL}") {
            conteudoEsquerdaUnchecked()
        }
        span(classes = "z-10 transition-all duration-300 px-4 py-2 peer-checked:hidden flex flex-row font-semibold items-center ${CoresEnum.ALERT_DARK.bg} peer-checked:${CoresEnum.TRANSPARENT.bg} ${CoresEnum.ALERT_LIGHT.text} peer-checked:${CoresEnum.LOW_PURE.text} ${ArredondamentosEnum.PILL}") {
            conteudoDireitaUnchecked()
        }
        span(classes = "z-10 transition-all duration-300 px-4 py-2 hidden peer-checked:flex flex-row font-semibold items-center ${CoresEnum.ALERT_DARK.bg} peer-checked:${CoresEnum.TRANSPARENT.bg} ${CoresEnum.ALERT_LIGHT.text} peer-checked:${CoresEnum.LOW_PURE.text} ${ArredondamentosEnum.PILL}") {
            conteudoDireitaChecked()
        }
    }
}