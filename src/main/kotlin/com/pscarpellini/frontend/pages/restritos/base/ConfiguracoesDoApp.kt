package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.configuracoesDoApp(
    sessao: SessaoUsuarioVO
) {
    includeHeaderLogado(sessao = sessao)
    includeContentGrid(
        linhas = 1,
        colunas = 1,
    ) {
        card(classes = "flex flex-col gap-12") {
            div(classes = "flex flex-col w-full gap-2") {
                h4(classes = "mb-2") { +"Dados da empresa" }
                div(classes = "grid grid-cols-2 gap-6 w-full") {
                    div {
                        h5 { +"Nome da empresa" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.nome ?: "") }
                    }
                    div {
                        h5 { +"Contato para suporte" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.email ?: "") }
                    }
                    div {
                        h5 { +"Serviços contratados" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.nome ?: "") }
                    }
                    div {
                        h5 { +"CNPJ" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.email ?: "") }
                    }
                }
            }
            div(classes = "flex flex-col w-full gap-2") {
                h4(classes = "mb-2") { +"Tema do aplicativo" }
                div(classes = "grid grid-cols-2 gap-6 w-full") {
                    div(classes = "") {
                        h5 { +"Logotipo da empresa" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.nome ?: "") }
                    }
                    div {
                        h5 { +"Contato para suporte" }
                        span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.email ?: "") }
                    }
                }
            }
        }
    }
    botaoLink(link = "#", classes = "self-end gap-2") {
        icone(IconesEnum.EDITAR, usarPadding = false)
        +"Editar perfil"
    }
}