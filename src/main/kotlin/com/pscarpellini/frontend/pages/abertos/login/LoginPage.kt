package com.pscarpellini.frontend.pages.abertos.login

import com.pscarpellini.AmbientController
import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.logo.includeLogoLumen
import com.pscarpellini.frontend.fragments.geral.toast.toastContainer
import com.pscarpellini.frontend.fragments.nao_logados.header_menu.includeHeaderMenu
import com.pscarpellini.rotas.PaginasAbertasEnum
import kotlinx.html.*

fun HTML.loginPage() {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf(),
        incluirScriptsFuncionais = false
    )
    body(
        classes = "bg-${CoresEnum.HIGH_LIGHT}"
    ) {
        toastContainer()
        div(classes = "flex flex-col h-screen") {
            includeHeaderMenu(
                classes = "flex flex-row space-between items-center"
            ) {
                includeLogoLumen()
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
            div(classes = "flex flex-row flex-grow rounded-lg bg-high-pure mx-6 lg:mx-12 mb-6 lg:mb-12") {
                div(classes = "flex-grow flex flex-row lg:items-center justify-center px-6 py-4") {
                    div(classes = "flex flex-col gap-2") {
                        h1(classes = "text-xl font-semibold text-low-pure") {
                            +"Olá, boas vindas à lumen"
                        }
                        p(classes = "text-low-medium text-sm mt-2") {
                            +"Faça login com seu usuário e senha cadastrados."
                        }

                        form(
                            classes = "w-full max-w-sm mt-6 space-y-4 flex-row",
                        ) {
                            div {
                                label(classes = "block text-sm font-medium text-low-pure") {
                                    htmlFor = "usuario"
                                    +"Usuário"
                                }
                                input(classes = "mt-1 w-full px-4 py-2 rounded-lg focus:ring-2 focus:ring-brand-pure focus:border-brand-pure bg-high-light") {
                                    type = InputType.text
                                    id = "usuario"
                                    name = "usuario"
                                    placeholder = "Digite seu usuário"
                                }
                            }

                            div {
                                label(classes = "block text-sm font-medium text-low-pure") {
                                    htmlFor = "password"
                                    +"Senha"
                                }
                                div {
                                    input(classes = "mt-1 w-full px-4 py-2 rounded-lg focus:ring-2 focus:ring-brand-pure focus:border-brand-pure bg-high-light") {
                                        type = InputType.password
                                        id = "password"
                                        name = "password"
                                        placeholder = "Digite sua senha"
                                    }
                                }
                            }

                            botao(
                                tipo = TiposBotaoEnum.PRIMARY,
                                classes = "w-full",
                                hxPath = PaginasAbertasEnum.Login.path,
                                hxTarget = "toast-container",
                                hxSwap = "beforeend"
                            ) { +"Entrar" }

                            botaoLink(
                                tipo = TiposBotaoEnum.SUBTLE,
                                small = true,
                                classes = "block",
                                link = PaginasAbertasEnum.EsqueciMinhaSenha.path
                            ) { +"Esqueci minha senha" }
                        }
                    }
                }
                img(classes = "hidden lg:block", src = "/static/images/imagem_login.svg", alt = "Lumen Apps")
            }
        }
    }
}