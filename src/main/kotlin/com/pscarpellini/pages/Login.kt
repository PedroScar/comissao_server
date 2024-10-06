package com.pscarpellini.pages

import com.pscarpellini.enums.EndpointsEnum
import kotlinx.html.*

fun HTML.loginPage(errorMessage: String? = null) {
    head {
        title("Login Page")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div(classes = "login-container") {
            div(classes = "login-panel") {
                h1(classes = "page-title") { +"Login" }
                if (errorMessage != null) {
                    p {
                        style = "color: red;"
                        +errorMessage
                    }
                }
                form(
                    classes = "form-main",
                    action = EndpointsEnum.LoginRequest.path,
                    method = EndpointsEnum.LoginRequest.method
                ) {
                    div(classes = "form-input") {
                        textInput(name = "username") { placeholder = "Usuário" }
                    }
                    div(classes = "form-input") {
                        passwordInput(name = "password") { placeholder = "Senha" }
                    }
                    div(classes = "form-button") {
                        button(type = ButtonType.submit) { +"Entrar" }
                    }
                }
            }
        }
    }
}