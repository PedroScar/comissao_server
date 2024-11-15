package pages

import enums.PagesEnum
import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.p
import kotlinx.html.style
import kotlinx.html.title
import models.Conta

fun HTML.comissaoHomePage(errorMessage: String? = null, successMessage: String? = null) {
    head {
        title("Home Page")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        if (errorMessage != null) {
            p {
                style = "color: red;"
                +errorMessage
            }
        }
        if (successMessage != null) {
            p {
                style = "color: green;"
                +successMessage
            }
        }
        div(classes = "sidebar") {
            a(href = PagesEnum.Home.path, classes = "sidebar-item") { +"Home" }
            a(href = PagesEnum.AddUser.path, classes = "sidebar-item") { +"Novo contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Editar contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Remover contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Adicionar saldo" }
            a(href = "#", classes = "sidebar-item") { +"Remover saldo" }
            a(href = "#", classes = "sidebar-item") { +"Sair" }
        }
        div(classes = "main-content") {
            h1 { +"Comissao" }
            p { +"This is the main content area." }
        }
    }
}