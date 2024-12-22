package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.ArredondamentosEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.HTML
import kotlinx.html.body

fun HTML.homePage(
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
                card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2") {
                    icone(IconesEnum.ADICIONAR, showBackground = true)
                    +"Criar nova promoção"
                }
                card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2") {
                    icone(IconesEnum.PRECO, showBackground = true)
                    +"Modificar saldo"
                }
                card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2") {
                    icone(IconesEnum.USUARIOS, showBackground = true)
                    +"Cadastrar novo usuário"
                }
                card(arredondamento = ArredondamentosEnum.PILL, classes = "gap-2") {
                    icone(IconesEnum.APP, showBackground = true)
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