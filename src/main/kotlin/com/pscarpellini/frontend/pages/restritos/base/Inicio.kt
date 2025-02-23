package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_pilula.itemMenuPilula
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.FragmentsRestritosEnum
import com.pscarpellini.rotas.WidgetsInicioEnum
import kotlinx.html.*

fun FlowContent.inicio(
    sessao: SessaoUsuarioVO
) {
    div(classes = "grow flex flex-col gap-6") {
        includeContentGrid(
            linhas = 1,
            colunas = 4,
            classes = "w-full"
        ) {
            if(sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.NOVA_PROMOCAO)) itemMenuPilula(ItensMenuPilulaEnum.CRIAR_NOVA_PROMOCAO)
            if(sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_SALDOS)) itemMenuPilula(ItensMenuPilulaEnum.MODIFICAR_SALDO)
            if(sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.CRIAR_USUARIO)) itemMenuPilula(ItensMenuPilulaEnum.CADASTRAR_NOVO_USUARIO)
            if(sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_CONFIGURACOES_DO_APP)) itemMenuPilula(ItensMenuPilulaEnum.CONFIGURACOES_DO_APP)
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
                    textoLoading = "Buscando promoções",
                    classes = "w-full grow h-60 overflow-y-auto overflow-x-hidden"
                )
            }
            card(classes = "flex flex-col gap-3") {
                div(classes = "flex flex-row w-full items-center justify-start") {
                    span(classes = "text-base font-semibold") { +"Visão geral" }
                    icone(IconesEnum.INFO, size = 2f)
                }
                autoLoaderFragment(
                    id = "contagens_dashboard",
                    path = WidgetsInicioEnum.CONTAGEM_PROMOCOES_WIDGET.path,
                    textoLoading = "Contando promoções",
                    classes = "w-full grow h-60 overflow-y-auto overflow-x-hidden"
                )
            }
            card(classes = "col-span-3 flex flex-col gap-3") {
                div(classes = "flex flex-row w-full items-center justify-start") {
                    span(classes = "text-base font-semibold") { +"Transações recentes" }
                    icone(IconesEnum.INFO, size = 2f)
                }
                autoLoaderFragment(
                    id = "tabela_de_transacoes",
                    path = WidgetsInicioEnum.HISTORICO_DE_TRANSACOES_WIDGET.path,
                    textoLoading = "Buscando transações",
                    classes = "w-full grow h-60 overflow-y-auto overflow-x-hidden"
                )
            }
        }
    }
}