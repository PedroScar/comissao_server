package com.pscarpellini.frontend.fragments.geral.html_header

import kotlinx.html.HEAD
import kotlinx.html.script

fun HEAD.includeScriptsFuncionais(
) {
//    NOTYF TOASTS
    script(src = "https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js") {}
    script(src = "/static/scripts/ToastManager.js") {}

//    HTMX
    script(src = "https://unpkg.com/htmx.org") {}
    script(src = "/static/scripts/HTMXConfigs.js") {}

//    AVATAR
    script(src = "/static/scripts/AvatarScript.js") {}
//    MENU DROPDOWN
    script(src = "/static/scripts/DropdownScript.js") {}
//    POPUP
    script(src = "/static/scripts/PopupScript.js") {}
//    FORMULÁRIOS
    script(src = "/static/scripts/FormularioScript.js") {}
//    HX ERROR HANDLER
    script(src = "/static/scripts/HXErrorHandler.js") {}
//    HX POPUP HANDLER
    script(src = "/static/scripts/HXPopupHandler.js") {}
}