package com.pscarpellini.frontend.pages.abertos.landingPage

import com.pscarpellini.AmbientController
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.logo.includeLogoLumen
import com.pscarpellini.frontend.fragments.nao_logados.header_menu.includeHeaderMenu
import com.pscarpellini.frontend.routes.abertos.RoutesAbertosEnum
import kotlinx.html.*

fun HTML.landingPage() {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body {
        includeHeaderMenu(
            id = "header",
            classes = "flex flex-row space-between items-center fixed top-0 bg-high-pure container"
        ) {
            includeLogoLumen()
            div(classes = "flex-row gap-2 hidden lg:flex") {
                button(classes = "botao-vazado hover:underline") {
                    attributes["id"] = "btn-vantagens"
                    +"Principais vantagens"
                }
                button(classes = "botao-vazado hover:underline") {
                    attributes["id"] = "btn-porque"
                    +"Por que usar nosso sistema?"
                }
            }
            div(classes = "flex flex-row gap-2 text-sm lg:text-lg") {
                form(
                    action = "https://wa.me/${AmbientController.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações",
                    method = FormMethod.get
                ) {
                    attributes["target"] = "_blank"
                    button(classes = "botao-especialista hover:bg-high-pure text-sm lg:text-lg", type = ButtonType.submit) {
                        +"Falar com especialista"
                        img(classes = "ic-whatsapp", src = "/static/ic_whatsapp.svg", alt = "Ícone")
                    }
                }

                form(action = RoutesAbertosEnum.Login.path, method = FormMethod.get) {
                    button(classes = "botao-verde hover:bg-brand-medium hidden lg:block", type = ButtonType.submit) { +"Entrar" }
                }
            }
        }

        div(classes = "conteudo-vertical-centralizado") {
            section(classes = "") {
                p(classes = "texto-landing-titulo text-xl lg:text-4xl mx-6 lg:mx-2") { +"Automatize comissões e \nimpulsione resultados" }
                p(classes = "texto-landing-subtitulo text-md lg:text-xl text-pretty mx-6 lg:mx-2 mt-2 lg:mt-4") { +"Gerencie comissões e promoções de forma simples e eficiente.\nTransparência para promotores, controle total para empresas. " }
                img(classes = "imagem-demonstracao1", src = "/static/img_demonstracao1.svg")
            }
            section(classes = "container") {
                attributes["id"] = "section_vantagens"
                p(classes = "texto-landing-titulo-section") {
                    +"PRINCIPAIS VANTAGENS"
                }
                p(classes = "texto-landing-subtitulo-section") { +"Soluções para empresas e promotores" }
                div("carrossel-landing flex flex-col items-start lg:flex-row gap-6 lg:gap-14") {
                    div("basis-1/4 flex flex-col items-start") {
                        img(classes = "imagem-carrossel w-full", src = "/static/img_carrossel_1.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"App personalizado" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Personalize o app com as cores e a logo da sua empresa" }
                    }
                    div("basis-1/4 flex flex-col items-start") {
                        img(classes = "imagem-carrossel w-full", src = "/static/img_carrossel_2.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Gestão de promoções" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Crie campanhas e acompanhe resultados" }
                    }
                    div("basis-1/4 flex flex-col items-start") {
                        img(classes = "imagem-carrossel w-full", src = "/static/img_carrossel_3.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Saldo e extrato" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Promotores acompanham seus ganhos em tempo real" }
                    }
                    div("basis-1/4 flex flex-col items-start") {
                        img(classes = "imagem-carrossel w-full", src = "/static/img_carrossel_4.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Relatórios" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Dados em tempo real para tomadas de decisão" }
                    }
                }
            }
            section(classes = "container") {
                attributes["id"] = "section_porque"
                p(classes = "texto-landing-titulo-section") {
                    +"POR QUE USAR NOSSO SISTEMA DE COMISSÕES?"
                }
                p(classes = "texto-landing-subtitulo-section") { +"Porque depois de começar a usar..." }
                div(classes = "flex flex-col lg:flex-row gap-4 lg:gap-0 px-4 mt-6") {
                    div("flex flex-col") {
                        p(classes = "texto-landing-carrossel-titulo2 text-center text-4xl md:text-5xl lg:text-7xl") { +"98%" }
                        p(classes = "texto-landing-carrossel-subtitulo2 text-md lg:text-lg text-pretty") { +"das empresas afirmam que economizam tempo na gestão" }
                    }
                    div("flex flex-col") {
                        p(classes = "texto-landing-carrossel-titulo2 text-center text-4xl md:text-5xl lg:text-7xl") { +"87%" }
                        p(classes = "texto-landing-carrossel-subtitulo2 text-md lg:text-lg text-pretty") { +"das equipes se sentem mais valorizadas com a transparência nas comissões" }
                    }
                    div("flex flex-col") {
                        p(classes = "texto-landing-carrossel-titulo2 text-center text-4xl md:text-5xl lg:text-7xl") { +"94%" }
                        p(classes = "texto-landing-carrossel-subtitulo2 text-md lg:text-lg text-pretty") { +"dos gestores reduzem erros manuais nas comissões e tomadas de decisões" }
                    }
                }
            }
            section(classes = "bg-brand-pure w-full flex mt-10") {
                div(classes = "container flex flex-col gap-4 content-center px-4 lg:px-12 py-8") {
                    includeLogoLumen(mostrarApenasIcone = true)
                    p(classes = "texto-landing-inferior_lumen text-pretty") {
                        +"Na "
                        strong(classes = "font-bold") { +"lumen" }
                        +" , transformamos desafios em soluções simples e eficazes, ajudando empresas a alcançarem seus objetivos com tecnologia e inovação."
                    }
                }
            }
        }
    }
}