package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.fragments.geral.icone.TipoBotaoEnum
import com.pscarpellini.frontend.fragments.geral.button.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import kotlinx.html.*

fun HTML.homePage(
    sessao: SessaoUsuarioVO
) {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal()
        includeContentBodyLogado {
            includeHeaderLogado(sessao)
            includeContentGrid(
                linhas = 1,
                colunas = 4,
                classes = "w-full"
            ) {
                card(arredondamento = ArredondamentosEnum.PILL) {
                    botao(tipo = TipoBotaoEnum.PRIMARY) { +"+" }
                    +"Criar nova promoção"
                }
                card(arredondamento = ArredondamentosEnum.PILL) {
                    botao(tipo = TipoBotaoEnum.PRIMARY) { +"+" }
                    +"Modificar saldo"
                }
                card(arredondamento = ArredondamentosEnum.PILL) {
                    botao(tipo = TipoBotaoEnum.PRIMARY) { +"+" }
                    +"Cadastrar novo usuário"
                }
                card(arredondamento = ArredondamentosEnum.PILL) {
                    botao(tipo = TipoBotaoEnum.PRIMARY) { +"+" }
                    +"Configurações do app"
                }
            }
            includeContentGrid(
                linhas = 2,
                colunas = 3,
                classes = "h-full"
            ) {
                card(classes = "col-span-2") {

                }
                card(classes = "") {

                }
                card(classes = "col-span-3") {

                }
            }
        }
    }
}