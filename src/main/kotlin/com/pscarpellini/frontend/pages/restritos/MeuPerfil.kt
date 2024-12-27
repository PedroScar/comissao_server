package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.TiposAvatarEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun HTML.meuPerfil(
    sessao: SessaoUsuarioVO
) {
    includeHtmlHeader()
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        includeContentBodyLogado {
            includeHeaderLogado(sessao = sessao, tituloPagina = "Meu perfil", mostrarBack = false)
            includeContentGrid(
                linhas = 1,
                colunas = 1,
            ) {
                card(classes = "flex flex-col") {
                    div(classes = "flex flex-row gap-6 justify-items-start") {
                        avatar(nome = sessao.cliente?.nome ?: "", imagemUrl = "", tipo = TiposAvatarEnum.EXTRA_LARGE_CIRCLE)
                        img(src = "", classes = "rounded-pill size-30")
                        div(classes = "flex flex-col gap-4") {
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
                    div {
                        +"Nome completo"
                    }
                }
            }
        }
    }
}