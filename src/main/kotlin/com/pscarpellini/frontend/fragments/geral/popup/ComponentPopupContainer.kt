package com.pscarpellini.frontend.fragments.geral.popup

import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.loading.loading
import kotlinx.html.FlowContent
import kotlinx.html.div

/**
 * Exiba uma mensagem toast na página.
 */
fun FlowContent.popupContainer(
) {
    div(classes = "fixed inset-0 ${CoresEnum.LOW_DARK.bg} bg-opacity-50 z-50 flex items-center justify-center duration-300 transition-all opacity-0 pointer-events-none") {
        attributes["id"] = "popup-overlay"
        div(classes = "${CoresEnum.HIGH_PURE.bg} ${ArredondamentosEnum.LG} w-full max-w-2xl max-h-[90vh] overflow-y-auto") {
            attributes["id"] = "popup"
            div {
                attributes["id"] = "popup-content"
                loading(id = "loading_popup")
            }
        }
    }
}