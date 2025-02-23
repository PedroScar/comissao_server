package com.pscarpellini.frontend.pages.abertos.senha

import com.pscarpellini.AmbientController
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposLogosEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.loading.loading
import com.pscarpellini.frontend.fragments.geral.logo.includeLogoLumen
import com.pscarpellini.frontend.fragments.nao_logados.header_menu.includeHeaderMenu
import com.pscarpellini.rotas.FragmentsAbertosEnum
import com.pscarpellini.rotas.PaginasAbertasEnum
import kotlinx.html.*

fun HTML.esqueciMinhaSenhaPage() {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf(),
    )
    body(
        classes = "bg-${CoresEnum.HIGH_LIGHT}"
    ) {
        div(classes = "flex flex-col h-screen") {
            includeHeaderMenu(
                classes = "flex flex-row space-between items-center"
            ) {
                includeLogoLumen(tipo = TiposLogosEnum.ESCRITA_PRETA, classes = "h-10")
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
                            +"Esqueceu sua senha?"
                        }
                        p(classes = "text-low-medium text-sm mt-2") {
                            +"Digite o seu endereço de e-mail cadastrado."
                        }

                        form(
                            classes = "w-full max-w-sm mt-6 space-y-4 flex-row relative",
                        ) {
                            div(classes = "absolute inset-0 ${CoresEnum.HIGH_PURE.bg} bg-opacity-75 flex items-center justify-center z-10 collapse") {
                                attributes["id"] = "loading-login"
                                loading(id = "loading-login", isVertical = true)
                            }

                            inputField(
                                label = "E-mail",
                                inputType = InputType.text,
                                nomeDoCampo = "email",
                                hint = "Digite seu e-mail"
                            )

                            botao(
                                tipo = TiposBotaoEnum.PRIMARY,
                                classes = "w-full",
                                hxPath = FragmentsAbertosEnum.FORMULARIO_REDEFINIR_SENHA,
                                hxSwap = "beforeend",
                                hxIndicator = "loading-login",
                            ) { +"Redefinir senha" }

                            botaoLink(
                                tipo = TiposBotaoEnum.SUBTLE,
                                small = true,
                                classes = "block",
                                link = PaginasAbertasEnum.Login.path
                            ) { +"Já lembrei! Fazer login agora" }
                        }
                    }
                }
                img(classes = "hidden lg:block", src = "/static/images/imagem_login.svg", alt = "Lumen Apps")
            }
        }
    }
}