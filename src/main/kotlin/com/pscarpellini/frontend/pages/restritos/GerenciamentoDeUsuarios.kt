package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.html_header.includeHtmlHeader
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.frontend.fragments.logados.content_body.includeContentBodyLogado
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeListaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.html.*

fun HTML.gerenciamentoDeUsuarios(
    sessao: SessaoUsuarioVO
) {
    includeHtmlHeader()
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        includeContentBodyLogado {
            includeHeaderLogado(sessao = sessao, tituloPagina = "Gerenciamento de usuários", mostrarBack = false)
            card(classes = "flex flex-col gap-8 h-full") {
                div(classes = "flex flex-row w-full items-center gap-4") {
                    inputField(
                        inputType = InputType.email,
                        enabled = true,
                        hint = "Pesquise pelo nome",
                        nomeDoCampo = "busca",
                        classes = "grow",
                        icone = IconesEnum.BUSCAR
                    )
                    botao(tipo = TiposBotaoEnum.NEUTRAL) {
                        icone(IconesEnum.FILTRO, usarPadding = false, size = 2f)
                        +"Filtro"
                    }
                    botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = PaginasRestritasEnum.NOVO_USUARIO.path) {
                        icone(IconesEnum.ADICIONAR, usarPadding = false, size = 2f)
                        +"Adicionar usuário"
                    }
                }
                autoLoaderFragment(
                    id = "tabela_de_usuarios",
                    path = PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS.path,
                    classes = "w-full grow"
                )
            }
        }
    }
}