package com.pscarpellini.frontend.fragments.geral.divider

import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.icone.icone
import kotlinx.html.*

fun FlowContent.divider() {
    hr(classes = "border ${CoresEnum.HIGH_MEDIUM.border} mx-4") {  }
}