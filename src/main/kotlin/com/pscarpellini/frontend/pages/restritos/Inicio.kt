package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.ItensMenuPilulaEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_pilula.itemMenuPilula
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.HTML
import kotlinx.html.a
import kotlinx.html.body

fun HTML.inicio(
    sessao: SessaoUsuarioVO
) {
    includeHtmlHeader(
        scriptsDaPagina = arrayListOf("/static/scripts/LandingPageScript.js")
    )
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        includeContentBodyLogado {
            includeHeaderLogado(sessao)
            includeContentGrid(
                linhas = 1,
                colunas = 4,
                classes = "w-full"
            ) {
                itemMenuPilula(ItensMenuPilulaEnum.CRIAR_NOVA_PROMOCAO)
                itemMenuPilula(ItensMenuPilulaEnum.MODIFICAR_SALDO)
                itemMenuPilula(ItensMenuPilulaEnum.CADASTRAR_NOVO_USUARIO)
                itemMenuPilula(ItensMenuPilulaEnum.CONFIGURACOES_DO_APP)
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