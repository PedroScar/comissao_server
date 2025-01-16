package com.pscarpellini.frontend.fragments.geral.divider

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import kotlinx.html.*

fun FlowContent.divider() {
    hr(classes = "border ${CoresEnum.HIGH_MEDIUM.border} mx-4") {  }
}