package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.TiposAvatarEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.meuPerfil(
    sessao: SessaoUsuarioVO
) {
    includeHeaderLogado(sessao = sessao, tituloPagina = "Meu perfil", mostrarBack = true)
    includeContentGrid(
        linhas = 1,
        colunas = 1,
    ) {
        card(classes = "flex flex-col gap-8") {
            div(classes = "flex flex-row gap-6 w-full") {
                avatar(nome = sessao.conta?.nome ?: "", imagemUrl = "", tipo = TiposAvatarEnum.EXTRA_LARGE_CIRCLE)
                img(src = "", classes = "rounded-pill size-30")
                div(classes = "flex flex-col gap-4 justify-center") {
                    span (classes = CoresEnum.LOW_LIGHT.text) {
                        +"Esta imagem é destinada apenas ao perfil interno e não será compartilhada"
                        br
                        +"externamente (o arquivo deve ter menos de 10MB)."
                    }
                    div (classes = "flex flex-row gap-4") {
                        botao { +"Alterar" }
                        botao(tipo = TiposBotaoEnum.NEUTRAL) { +"Remover" }
                    }
                }
            }

            div(classes = "grid grid-cols-2 grid-rows-2 gap-6 w-full") {
                div {
                    h5 { +"Nome completo" }
                    span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.nome ?: "") }
                }
                div {
                    h5 { +"Email" }
                    span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.email ?: "") }
                }
                div {
                    h5 { +"CPF" }
                    span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.cpf ?: "") }
                }
                div {
                    h5 { +"Telefone" }
                    span (classes = CoresEnum.LOW_LIGHT.text) { +(sessao.conta?.telefone ?: "") }
                }
            }

            div(classes = "flex flex-col gap-4 w-full") {
                h2 { +"Senha" }
                span (classes = CoresEnum.LOW_LIGHT.text) {
                    +"Proteja sua conta com uma senha exclusiva. Lembre-se, você pode atualizá-la"
                    br
                    +"sempre que necessário."
                }
                botaoLink(link = "#", tipo = TiposBotaoEnum.NEUTRAL, classes = "self-start") { +"Alterar senha" }
            }
        }
    }
    botaoLink(link = "#", classes = "self-end gap-2") {
        icone(IconesEnum.EDITAR, usarPadding = false)
        +"Editar perfil"
    }
}