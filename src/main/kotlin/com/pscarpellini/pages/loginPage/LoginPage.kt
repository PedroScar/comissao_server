package com.pscarpellini.pages.loginPage

import com.pscarpellini.AmbientController
import com.pscarpellini.components.header_menu.headerMenu
import kotlinx.html.*

fun HTML.loginPage() {
    head {
        title("Lumen Apps")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        link(
            href = "https://fonts.googleapis.com/css2?family=Nunito:wght@400;500;600;700&display=swap",
            rel = "stylesheet"
        )
        meta(name = "viewport", content = "width=device-width, initial-scale=1.0")
        script(src = "https://cdn.tailwindcss.com") {}
        script(src = "/static/scripts/TailWindScript.js") {}
        script(src = "/static/scripts/LandingPageScript.js") {}
    }
    body(
        classes = "bg-high-light"
    ) {
        div(classes = "flex flex-col h-screen") {
            headerMenu(
                classes = "flex flex-row space-between items-center"
            ) {
                img(classes = "header-logo hidden lg:block", src = "/static/header_lumen.svg", alt = "Lumen Apps")
                img(classes = "lg:hidden", src = "/static/logo_preto.svg", alt = "Lumen Apps")
                div(classes = "flex flex-row gap-2 text-sm lg:text-lg") {
                    +"Alguma dúvida?"
                    a(
                        classes = "botao-login-contato hover:underline",
                        href = "https://wa.me/${AmbientController.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações"
                    ) {
                        attributes["target"] = "_blank"
                        +"Entre em contato"
                    }
                }
            }
            div(classes = "flex flex-row flex-grow rounded-3xl bg-high-pure mx-6 lg:mx-12 mb-6 lg:mb-12") {
                div(classes = "flex-grow flex flex-row items-center justify-center ") {
                    div(classes = "flex flex-col") {
                        h1(classes = "text-xl font-semibold text-low-pure") {
                            +"Olá, boas vindas à lumen"
                        }
                        p(classes = "text-low-medium text-sm mt-2") {
                            +"Faça login com seu usuário e senha cadastrados."
                        }
                        form(classes = "w-full max-w-sm mt-6 space-y-4") {
                            div {
                                label(classes = "block text-sm font-medium text-low-pure") {
                                    htmlFor = "usuário"
                                    +"Usuário"
                                }
                                input(classes = "mt-1 w-full px-4 py-2 rounded-2xl focus:ring-2 focus:ring-brand-pure focus:border-brand-pure bg-high-light") {
                                    type = InputType.text
                                    id = "usuário"
                                    placeholder = "Digite seu usuário"
                                }
                            }

                            div {
                                label(classes = "block text-sm font-medium text-low-pure") {
                                    htmlFor = "password"
                                    +"Senha"
                                }
                                div {
                                    input(classes = "mt-1 w-full px-4 py-2 rounded-2xl focus:ring-2 focus:ring-brand-pure focus:border-brand-pure bg-high-light") {
                                        type = InputType.password
                                        id = "password"
                                        placeholder = "Digite sua senha"
                                    }
                                    button(classes = "absolute inset-y-0 right-3 flex items-center") {
                                        type = ButtonType.button
                                    }
                                }
                            }

                            button(classes = "w-full py-2 px-4 bg-brand-pure text-low-pure rounded-full transition-all hover:bg-brand-medium") {
                                type = ButtonType.submit
                                +"Entrar"
                            }

                            button(classes = "w-full text-sm text-low-pure hover:underline") {
                                type = ButtonType.button
                                +"Esqueci minha senha"
                            }
                        }
                    }
                }
                img(classes = "hidden lg:block", src = "/static/images/imagem_login.svg", alt = "Lumen Apps")
            }
        }
    }
}