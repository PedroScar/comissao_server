package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.PosicoesDropdownEnum
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownDivider
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownItem
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeHeaderLogado(
    sessao: SessaoUsuarioVO,
    tituloPagina: String = "",
    mostrarBack: Boolean = false,
) {
    card(
        showBackground = false,
        classes = "w-full flex items-center"
    ) {
        if(!mostrarBack) {
            a(href = PaginasRestritasEnum.INICIO.path, classes = "hover:underline") {
                icone(IconesEnum.ARROW_LEFT, size = 2)
            }
        }
        span(classes = "font-semibold text-xl") {
            if(tituloPagina.isBlank()) +"Olá, ${sessao.cliente?.nome}"
            else +tituloPagina
        }
        spacer()
        a(href = "", classes = "hover:underline") {
            card(showBackground = false, usarPadding = false, classes = "mx-6") {
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
            opcoes = arrayListOf(
                DropdownItem(nome = "Meu perfil", link = PaginasRestritasEnum.MEU_PERFIL.path),
                DropdownDivider(),
                DropdownItem(nome = "Sair da conta", link = PaginasRestritasEnum.LOGOUT.path, corTexto = CoresEnum.ALERT_DARK),
            )
        )
    }
}