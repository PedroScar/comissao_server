package com.pscarpellini.frontend.fragments.geral.html_header

import kotlinx.html.*

fun HTML.includeHtmlHeader(
    nomeDaPagina: String = "Lumen Apps",
    scriptsDaPagina: ArrayList<String> = arrayListOf(),
) {
    head {
        title(nomeDaPagina)
        link(rel = "stylesheet", href = "https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        link(rel = "icon", type = "image/x-icon", href = "/static/favicon.ico")
        link(
            href = "https://fonts.googleapis.com/css2?family=Nunito:wght@400;500;600;700&display=swap",
            rel = "stylesheet"
        )
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")

//        Validação da Google Pay Console
        meta(name = "google-site-verification", content = "P-OEr1Y2lxlQFC89WARfWDLoL-wgbZmQultRF0HCcGs")

//        TailwindCSS
        script(src = "https://cdn.tailwindcss.com") {}
        script(src = "/static/scripts/TailwindScript_tmp.js") {}

        includeScriptsFuncionais()

        scriptsDaPagina.forEach { script(src = it) {} }
    }
}