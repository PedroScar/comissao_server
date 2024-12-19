package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.backend.enums.ContaStatusEnum
import com.pscarpellini.backend.enums.ContaTipoEnum
import com.pscarpellini.backend.enums.endpoints.EndpointsLogadosEnum
import com.pscarpellini.frontend.routes.restritos.RoutesRestritosEnum
import kotlinx.html.*


fun HTML.addUserPage(errorMessage: String? = null) {
    head {
        title("Adicionar Usuário")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
    }
    body {
        div(classes = "sidebar") {
            a(href = RoutesRestritosEnum.Home.path, classes = "sidebar-item") { +"Home" }
            a(href = RoutesRestritosEnum.AddUser.path, classes = "sidebar-item") { +"Novo contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Editar contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Remover contribuidor" }
            a(href = "#", classes = "sidebar-item") { +"Adicionar saldo" }
            a(href = "#", classes = "sidebar-item") { +"Remover saldo" }
            a(href = "#", classes = "sidebar-item") { +"Sair" }
        }
        div(classes = "main-content") {
            h1(classes = "page-title") { +"Adicionar Usuário" }

            if (errorMessage != null) {
                div(classes = "error-message") {
                    +errorMessage
                }
            }

            form(
                action = EndpointsLogadosEnum.CriarUserRequest.path,
                method = EndpointsLogadosEnum.CriarUserRequest.method
            ) {
                div(classes = "form-input") {
                    input(type = InputType.text, name = "nome") {
                        placeholder = "Nome"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.text, name = "endereco") {
                        placeholder = "Endereço"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.text, name = "cpf") {
                        placeholder = "CPF"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.email, name = "email") {
                        placeholder = "Email"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.tel, name = "telefone") {
                        placeholder = "Telefone"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.text, name = "username") {
                        placeholder = "Username"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    input(type = InputType.password, name = "password") {
                        placeholder = "Password"
                        required = true
                    }
                }
                div(classes = "form-input") {
                    label { +"Status:" }
                    select(classes = "status") {
                        ContaStatusEnum.entries.forEach { conta ->
                            option {
                                value = conta.name
                                +conta.name
                            }
                        }
                    }
                }
                div(classes = "form-input") {
                    label { +"Tipo:" }
                    select(classes = "tipo") {
                        ContaTipoEnum.entries.forEach { conta ->
                            option {
                                value = conta.name
                                +conta.name
                            }
                        }
                    }
                }
                div(classes = "form-button") {
                    button(type = ButtonType.submit) {
                        +"Adicionar Usuário"
                    }
                }
            }
        }
    }
}