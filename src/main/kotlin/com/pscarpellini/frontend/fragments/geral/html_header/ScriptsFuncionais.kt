package com.pscarpellini.frontend.fragments.geral.html_header

import kotlinx.html.HEAD
import kotlinx.html.script

fun HEAD.includeScriptsFuncionais(
) {

//    HTMX
    script(src = "https://unpkg.com/htmx.org") {}

//    MENU DROPDOWN
    script(src = "/static/scripts/DropdownScript.js") {}
}