package com.pscarpellini.frontend.pages.abertos.politicaprivacidade

import com.pscarpellini.AmbientController
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposLogosEnum
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.logo.includeLogoLumen
import com.pscarpellini.frontend.fragments.nao_logados.header_menu.includeHeaderMenu
import kotlinx.html.*

fun HTML.politicaPrivacidadePage() {
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
                    div(classes = "flex flex-col gap-4") {
                        h1(classes = "text-xl font-semibold text-low-pure") {
                            +"Políticas de privacidade"
                        }

                        p(classes = "text-low-medium text-sm") {
                            +"Na Lumen, estamos comprometidos com a proteção e privacidade dos dados de nossos clientes e colaboradores. Esta Política de Privacidade explica como coletamos, usamos, protegemos e compartilhamos suas informações quando você utiliza nosso aplicativo."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"1. Coleta de Dados Pessoais"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"A Lumen pode coletar informações pessoais, como nome, e-mail, número de telefone, entre outros, conforme necessário para fornecer os serviços oferecidos no aplicativo. No entanto, apenas coletamos dados relevantes e com a devida autorização."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"2. Uso dos Dados"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"Os dados coletados são utilizados exclusivamente para fins de melhorar a experiência do usuário e fornecer os serviços solicitados. A Lumen não utiliza os dados de forma maliciosa ou para finalidades não autorizadas pelos usuários."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"3. Compartilhamento de Dados"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"A Lumen não compartilha, vende ou fornece os dados pessoais de clientes ou colaboradores a terceiros, salvo em situações onde seja legalmente exigido ou com o devido consentimento dos usuários."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"4. Monitoramento de Dados e Localização"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"A Lumen não monitora os dados de localização ou qualquer outra informação pessoal de clientes ou colaboradores, seja de forma ativa ou passiva. Não realizamos o rastreamento da localização ou comportamento dos usuários dentro ou fora do aplicativo."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"5. Proteção de Dados"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"A Lumen adota medidas de segurança adequadas para proteger os dados pessoais contra acessos não autorizados, uso indevido ou perda. Embora façamos o possível para proteger suas informações, nenhum sistema de segurança é 100% infalível."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"6. Alterações nesta Política de Privacidade"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"A Lumen pode atualizar esta Política de Privacidade periodicamente para refletir mudanças em nossos serviços ou na legislação aplicável. Quaisquer alterações serão publicadas neste documento, e recomendamos que você o consulte regularmente."
                        }

                        h2(classes = "text-base font-semibold text-low-pure mt-4") {
                            +"7. Contato"
                        }
                        p(classes = "text-low-medium text-sm") {
                            +"Se você tiver dúvidas ou preocupações sobre esta Política de Privacidade ou sobre o uso de seus dados, entre em contato conosco através do e-mail pedro.scarpellini@gmail.com."
                        }

                        p(classes = "text-low-medium text-sm mt-4") {
                            +"Obrigado por utilizar nosso aplicativo!"
                        }

                        p(classes = "text-low-soft text-xs") {
                            +"Políticas atualizadas em 05/01/2025"
                        }
                    }

                }
            }
        }
    }
}