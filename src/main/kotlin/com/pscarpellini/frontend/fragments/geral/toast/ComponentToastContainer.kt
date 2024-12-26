package com.pscarpellini.frontend.fragments.geral.toast

import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.toastContainer(id: String = "toast-container") {
    div(classes = "fixed bottom-4 left-1/2 transform -translate-x-1/2 flex flex-col space-y-2 items-center") {
        attributes["id"] = id
    }
}
