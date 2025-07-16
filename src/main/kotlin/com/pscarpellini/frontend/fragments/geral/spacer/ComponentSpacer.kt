package com.pscarpellini.frontend.fragments.geral.spacer

import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.spacer(isGrow: Boolean = true, horizontal: Boolean = true, classes: String = "") {
    div(classes = "$classes ${if(isGrow) "flex-grow" else {
        if(horizontal) "w-8" else "h-8"
    }}") {}
}