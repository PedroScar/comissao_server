package com.pscarpellini.pages

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
        script(src = "https://cdn.tailwindcss.com") {}
        script {
            unsafe {
                raw("""
                    tailwind.config = {
                      theme: {
                        extend: {
                          colors: {
                            "brand-pure": "#6BD9D5",
                            "brand-light": "#EDFCFD",
                            "brand-medium": "#B3F7FC",
                            "brand-dark": "#007F87",
                            
                            "highlight-pure": "#C9F56A",
                            "highlight-light": "#F1FCD9",
                            "highlight-medium": "#87BF0D",
                            "highlight-dark": "#517308",
                            
                            "low-pure": "#1F1F1F",
                            "low-light": "#808080",
                            "low-medium": "#4D4D4D",
                            "low-dark": "#141414",
                            
                            "high-pure": "#FAFAFA",
                            "high-light": "#F5F5F5",
                            "high-medium": "#E0E0E0",
                            "high-dark": "#D1D1D1",
                            
                            "alert-pure": "#FF3D00",
                            "alert-light": "#FBF1EF",
                            "alert-medium": "#FFC0AE",
                            "alert-dark": "#D80000",
                            
                            "warning-pure": "#FFC107",
                            "warning-light": "#FDF4E3",
                            "warning-medium": "#FFE291",
                            "warning-dark": "#936800",
                            
                            "success-pure": "#4CAF50",
                            "success-light": "#EFF5EF",
                            "success-medium": "#C1E2C0",
                            "success-dark": "#1F7827",
                            
                            "color-01": "#00A3E0",
                            "color-02": "#DC143C",
                            "color-03": "#32CD32",
                            "color-04": "#FFD700",
                            "color-05": "#0759AB",
                            "color-06": "#A52A2A",
                            "color-07": "#008080",
                            "color-08": "#FF5722",
                          }
                        }
                      }
                    }
                """)
            }
        }
    }
    body {
        div(classes = "landing-header") {
            img(classes = "header-logo", src = "/static/header_lumen.svg", alt = "Lumen Apps")

            div(classes = "botoes-container") {
                button(classes = "botao-vazado") {
                    attributes["id"] = "btn-vantagens"
                    +"Principais vantagens"
                }
                button(classes = "botao-vazado") {
                    attributes["id"] = "btn-porque"
                    +"Por que usar nosso sistema?"
                }
            }

            div(classes = "botoes-container") {
                form(
                    action = "https://wa.me/${Sessao.encaminhamentoWhatsapp}?text=Olá,%20gostaria%20de%20mais%20informações",
                    method = FormMethod.get
                ) {
                    attributes["target"] = "_blank"
                    button(classes = "py-3 px-6 bg-high-light rounded-full flex items-center transition-all hover:scale-105", type = ButtonType.submit) {
                        +"Falar com especialista"
                        img(classes = "ic-whatsapp", src = "/static/ic_whatsapp.svg", alt = "Ícone")
                    }
                }

                form(action = PagesEnum.Login.path, method = FormMethod.get) {
                    button(classes = "ms-2 py-3 px-6 bg-brand-pure rounded-full transition-all hover:scale-105", type = ButtonType.submit) { +"Entrar" }
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
//                OLHA ESSE CÓDIGO AQUI
                div(classes = "bg-brand-pure w-full py-14 px-36 mt-24") {
                    img(classes = "img-logo-preto", src = "/static/logo_preto.svg")
                    p(classes = "texto-landing-inferior_lumen") {
                        +"Na "
                        span(classes = "font-bold") { +"lumen" }
                        +" , transformamos desafios em soluções simples e eficazes, ajudando empresas a alcançarem seus objetivos com tecnologia e inovação."
                    }
                }
            }
        }
        script {
            unsafe {
                raw(
                    """
                    document.addEventListener("DOMContentLoaded", function() {
                        document.getElementById("btn-vantagens").addEventListener("click", function() {
                            const targetElement = document.getElementById("txt-vantagens");
                            const headerHeight = document.querySelector(".landing-header").offsetHeight;
                            const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight;

                            window.scrollTo({
                                top: targetPosition,
                                behavior: "smooth"
                            });
                        });
                        
                        document.getElementById("btn-porque").addEventListener("click", function() {
                            const targetElement = document.getElementById("txt-porque");
                            const headerHeight = document.querySelector(".landing-header").offsetHeight;
                            const targetPosition = targetElement.getBoundingClientRect().top + window.scrollY - headerHeight;

                            window.scrollTo({
                                top: targetPosition,
                                behavior: "smooth"
                            });
                        });
                    });
                """
                )
            }

        }
    }
}