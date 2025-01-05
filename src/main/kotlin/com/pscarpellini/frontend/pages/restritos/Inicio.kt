package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_pilula.itemMenuPilula
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.FragmentsRestritosEnum
import com.pscarpellini.rotas.PaginasRestritasEnum
import com.pscarpellini.rotas.WidgetsInicioEnum
import kotlinx.html.*

fun FlowContent.inicio(
    sessao: SessaoUsuarioVO
) {
    includeHeaderLogado(sessao = sessao)
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
        classes = "grow"
    ) {
        card(classes = "col-span-2 flex flex-col gap-3") {
            div(classes = "flex flex-row w-full items-center justify-start") {
                span(classes = "text-base font-semibold") { +"Promoções mais utilizadas" }
                icone(IconesEnum.INFO, size = 2f)
            }
            autoLoaderFragment(
                id = "promocoes_mais_utilizadas",
                path = WidgetsInicioEnum.PROMOCOES_WIDGET.path,
                classes = "w-full grow"
            )
        }
        card(classes = "flex flex-col gap-3") {
            div(classes = "flex flex-row w-full items-center justify-start") {
                span(classes = "text-base font-semibold") { +"Visão geral" }
                icone(IconesEnum.INFO, size = 2f)
            }
            autoLoaderFragment(
                id = "tabela_de_usuarios",
                path = FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path,
                classes = "w-full grow"
            )
        }
        card(classes = "col-span-3 flex flex-col gap-3") {
            div(classes = "flex flex-row w-full items-center justify-start") {
                span(classes = "text-base font-semibold") { +"Transações recentes" }
                icone(IconesEnum.INFO, size = 2f)
            }
            autoLoaderFragment(
                id = "tabela_de_usuarios",
                path = FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path,
                classes = "w-full grow"
            )
        }
    }
}