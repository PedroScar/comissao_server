package com.pscarpellini.frontend.fragments.logados.menu_principal

import com.pscarpellini.frontend.fragments.logados.menu_principal.menu_categoria.includeMenuCategoria
import com.pscarpellini.frontend.fragments.logados.menu_principal.menu_item.includeMenuItem
import kotlinx.html.BODY
import kotlinx.html.FlowContent
import kotlinx.html.div


fun FlowContent.includeMenuPrincipal() {
    div(classes = "flex flex-col h-screen w-80 p-4 bg-high-pure px-6") {
        div("flex items-center gap-2 font-bold text-lg mb-4") {
            +"🔗 Comissão"
        }

        div("flex-grow overflow-y-auto space-y-1") {
            includeMenuItem(
                nome = "Início",
                icone = "\uD83C\uDFE0",
                link = "#inicio"
            )
            includeMenuItem(
                nome = "Promoções",
                icone = "\uD83D\uDED2",
                link = "#promocoes"
            )
            includeMenuItem(
                nome = "Saldos dos promotores",
                icone = "\uD83D\uDCB5",
                link = "#saldos_dos_promotores"
            )
            includeMenuItem(
                nome = "Relatórios",
                icone = "\uD83D\uDCC4",
                link = "#relatorios"
            )
            includeMenuCategoria("Administração")
            includeMenuItem(
                nome = "Gerenciamento de usuários",
                icone = "\uD83D\uDCC4",
                link = "#relatorios"
            )
            includeMenuItem(
                nome = "Configurações do app",
                icone = "\uD83D\uDCC4",
                link = "#relatorios"
            )
            includeMenuItem(
                nome = "Histórico de transações",
                icone = "\uD83D\uDCC4",
                link = "#relatorios"
            )
        }
    }
}