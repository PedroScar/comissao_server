package com.pscarpellini.frontend.fragments.geral.divider

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import kotlinx.html.*

fun FlowContent.divider(useMargin: Boolean = true, classes: String = "") {
    hr(classes = "border ${CoresEnum.HIGH_MEDIUM.border} ${if(useMargin) "mx-4" else ""} $classes") {  }
}