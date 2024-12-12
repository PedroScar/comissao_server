package com.pscarpellini.pages.landingPage

import com.pscarpellini.enums.PagesEnum
import com.pscarpellini.session.Sessao
import kotlinx.html.*

fun HTML.landingPage() {
    head {
        title("Lumen Apps")
        link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        link(
            href = "https://fonts.googleapis.com/css2?family=Nunito:wght@400;500;600;700&display=swap",
            rel = "stylesheet"
        )
        script {
            unsafe {
                raw(addLandingPageScript())
            }
        }
    }
    body {
        div(classes = "landing-header") {
            img(classes = "header-logo", src = "/static/header_lumen.svg", alt = "Lumen Apps")

            div(classes = "linearLayoutHorizontal") {
                button(classes = "botao-vazado") {
                    attributes["id"] = "btn-vantagens"
                    +"Principais vantagens"
                }
                button(classes = "botao-vazado") {
                    attributes["id"] = "btn-porque"
                    +"Por que usar nosso sistema?"
                }
            }

            div(classes = "linearLayoutHorizontal") {
                form(
                    action = "https://wa.me/${Sessao.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações",
                    method = FormMethod.get
                ) {
                    attributes["target"] = "_blank"
                    button(classes = "botao-especialista", type = ButtonType.submit) {
                        +"Falar com especialista"
                        img(classes = "ic-whatsapp", src = "/static/ic_whatsapp.svg", alt = "Ícone")
                    }
                }

                form(action = PagesEnum.Login.path, method = FormMethod.get) {
                    button(classes = "botao-verde", type = ButtonType.submit) { +"Entrar" }
                }
            }
        }
        div(classes = "conteudo-vertical-centralizado") {
            p(classes = "texto-landing-titulo") { +"Automatize comissões e \nimpulsione resultados" }
            p(classes = "texto-landing-subtitulo") { +"Gerencie comissões e promoções de forma simples e eficiente.\nTransparência para promotores, controle total para empresas. " }
            img(classes = "imagem-demonstracao1", src = "/static/img_demonstracao1.svg")
            div(classes = "conteudo-vertical-start") {
                p(classes = "texto-landing-vantagens") {
                    attributes["id"] = "txt-vantagens"
                    +"PRINCIPAIS VANTAGENS"
                }
                p(classes = "texto-landing-solucoes") { +"Soluções para empresas e promotores" }
                div("carrossel-landing") {
                    div("carrossel-item") {
                        img(classes = "imagem-carrossel", src = "/static/img_carrossel_1.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"App personalizado" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Personalize o app com as cores e a logo da sua empresa" }
                    }
                    div("carrossel-item") {
                        img(classes = "imagem-carrossel", src = "/static/img_carrossel_2.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Gestão de promoções" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Crie campanhas e acompanhe resultados" }
                    }
                    div("carrossel-item") {
                        img(classes = "imagem-carrossel", src = "/static/img_carrossel_3.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Saldo e extrato" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Promotores acompanham seus ganhos em tempo real" }
                    }
                    div("carrossel-item") {
                        img(classes = "imagem-carrossel", src = "/static/img_carrossel_4.svg")
                        p(classes = "texto-landing-carrossel-titulo") { +"Relatórios" }
                        p(classes = "texto-landing-carrossel-subtitulo") { +"Dados em tempo real para tomadas de decisão" }
                    }
                }
                p(classes = "texto-landing-porque-titulo") {
                    attributes["id"] = "txt-porque"
                    +"POR QUE USAR NOSSO SISTEMA DE COMISSÕES?"
                }
                p(classes = "texto-landing-porque-subtitulo") { +"Porque depois de começar a usar..." }
                div("carrossel-landing") {
                    div("carrossel-item2") {
                        p(classes = "texto-landing-carrossel-titulo2") { +"98%" }
                        p(classes = "texto-landing-carrossel-subtitulo2") { +"das empresas afirmam que economizam tempo na gestão" }
                    }
                    div("carrossel-item2") {
                        p(classes = "texto-landing-carrossel-titulo2") { +"87%" }
                        p(classes = "texto-landing-carrossel-subtitulo2") { +"das equipes se sentem mais valorizadas com a transparência nas comissões" }
                    }
                    div("carrossel-item2") {
                        p(classes = "texto-landing-carrossel-titulo2") { +"94%" }
                        p(classes = "texto-landing-carrossel-subtitulo2") { +"dos gestores reduzem erros manuais nas comissões e tomadas de decisões" }
                    }
                }
                div(classes = "fundo-inferior") {
                    img(classes = "img-logo-preto", src = "/static/logo_preto.svg")
                    p(classes = "texto-landing-inferior_lumen") {
                        +"Na "
                        strong {
                            style = "font-weight: 700;"
                            +"lumen"
                        }
                        +" , transformamos desafios em soluções simples e eficazes, ajudando empresas a alcançarem seus objetivos com tecnologia e inovação."
                    }
                }
            }
        }
    }
}