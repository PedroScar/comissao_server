package com.pscarpellini.frontend.fragments.logados.header_logado

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.PosicoesDropdownEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botaoHX
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownDivider
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownItem
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownItemLink
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.navigation.navigationLink
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.frontend.fragments.logados.breadcrumbs.breadcrumbs
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.span

fun FlowContent.includeHeaderLogado(
    sessao: SessaoUsuarioVO,
) {
    val idDoHeader = "header-interno"
    div(classes = "w-full flex flex-col mb-0") {
        attributes["id"] = idDoHeader
        attributes["hx-swap-oob"] = "true"
        card(
            showBackground = false,
            classes = "w-full flex items-center px-0"
        ) {
            if (sessao.paginaAtual.showBack) {
                a(href = "javascript:history.back()", classes = "hover:underline") {
                    icone(IconesEnum.ARROW_LEFT, size = 2f)
                }
            }
            span(classes = "font-semibold text-xl") {
                if (sessao.paginaAtual == PaginasRestritasEnum.INICIO) +"Olá, ${sessao.conta?.nome}"
                else +sessao.paginaAtual.titulo
            }
            spacer()
            navigationLink(icone = IconesEnum.AJUDA, texto = "Central de ajuda", classes = "mx-6")
            dropdown(
                posicao = PosicoesDropdownEnum.DIREITA,
                botao = {
                    div(classes = "flex flex-row") {
                        icone(IconesEnum.USUARIO, showBackground = true, corFundo = CoresEnum.HIGH_PURE)
                    }
                },
                opcoes = arrayListOf(
                    DropdownItem(nome = "Meu perfil", link = CaminhosBaseEnum.MEU_PERFIL),
                    DropdownDivider(),
                    DropdownItemLink(nome = "Sair da conta", link = CaminhosBaseEnum.LOGOUT, corTexto = CoresEnum.ALERT_DARK),
                )
            )
        }
        if (sessao.paginaAtual.showBreadcrumbs && sessao.paginaAtual.breadcrumbs.isNotEmpty()) breadcrumbs(sessao.paginaAtual)
        if (sessao.paginaAtual.sublinks.isNotEmpty()) {
            div(classes = "flex") {
                sessao.paginaAtual.sublinks.forEach {
                    if(it.papelNecessario == null || sessao.papeisDeAcesso.contains(it.papelNecessario)) {
                        botaoHX(tipo = TiposBotaoEnum.PRIMARY, link = it.caminho, small = true) {
                            icone(it.icone, size = 2f)
                            +it.nome
                        }
                    }
                }
            }
        }
    }
}