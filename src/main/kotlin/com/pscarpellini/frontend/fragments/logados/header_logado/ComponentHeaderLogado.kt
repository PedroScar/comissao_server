package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.PosicoesDropdownEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdownItem
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeHeaderLogado(
    sessao: SessaoUsuarioVO,
    tituloPagina: String = "",
) {
    card(
        showBackground = false,
        classes = "w-full flex items-center"
    ) {
        span(classes = "font-semibold text-xl") { tituloPagina.ifEmpty { +"Olá, ${sessao.cliente?.nome}" } }
        spacer()
        a(href = "", classes = "hover:underline") {
            card(showBackground = false, usarPadding = false, classes = "mx-4") {
                icone(IconesEnum.AJUDA, size = 2)
                +"Central de ajuda"
            }
        }
        dropdown(
            posicao = PosicoesDropdownEnum.DIREITA,
            botao = {
                div(classes = "flex flex-row") {
                    icone(IconesEnum.USUARIO, showBackground = true, corFundo = CoresEnum.HIGH_PURE)
                }
            },
            dropdown = {
                dropdownItem(nome = "Meu perfil", link = "")
                dropdownItem(nome = "Sair da conta", link = "/logout", corTexto = CoresEnum.ALERT_DARK)
            }
        )
    }
}