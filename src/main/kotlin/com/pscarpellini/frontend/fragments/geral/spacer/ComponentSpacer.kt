package com.pscarpellini.frontend.fragments.geral.spacer

import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.includeSpacer() {
    div(classes = "flex-grow") {}
}