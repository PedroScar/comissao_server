package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.*
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
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun HTML.gerenciamentoDeUsuarios(
    sessao: SessaoUsuarioVO
) {
    val usuarios: ArrayList<UsuarioMock> = arrayListOf(
        UsuarioMock(nome = "Pedro Scarpellini", tipo = "Administrador", ultimoAcesso = "Ontem", acoes = arrayListOf("Visualizar")),
        UsuarioMock(nome = "Otávio Luiz", tipo = "Administrador", ultimoAcesso = "Hoje", acoes = arrayListOf("Editar", "Visualizar")),
        UsuarioMock(nome = "Zé da Manga", tipo = "Pedreiro", ultimoAcesso = "Semana passada", acoes = arrayListOf("Editar")),
        UsuarioMock(nome = "Borboleto", tipo = "Pintor", ultimoAcesso = "Segunda-feira", acoes = arrayListOf("Visualizar")),
        UsuarioMock(nome = "Zé da Manga", tipo = "Pedreiro", ultimoAcesso = "Semana passada", acoes = arrayListOf("Editar", "Visualizar")),
        UsuarioMock(nome = "Borboleto", tipo = "Pintor", ultimoAcesso = "Segunda-feira", acoes = arrayListOf("Editar", "Visualizar")),
        UsuarioMock(nome = "Zé da Manga", tipo = "Pedreiro", ultimoAcesso = "Semana passada", acoes = arrayListOf("Editar")),
        UsuarioMock(nome = "Borboleto", tipo = "Pintor", ultimoAcesso = "Segunda-feira", acoes = arrayListOf("Visualizar")),
        UsuarioMock(nome = "Zé da Manga", tipo = "Pedreiro", ultimoAcesso = "Semana passada", acoes = arrayListOf("Visualizar")),
        UsuarioMock(nome = "Borboleto", tipo = "Pintor", ultimoAcesso = "Segunda-feira", acoes = arrayListOf("Visualizar")),
    )

    includeHtmlHeader()
    body(
        classes = "bg-high-light flex flex-row"
    ) {
        includeMenuPrincipal(sessao)
        includeContentBodyLogado {
            includeHeaderLogado(sessao = sessao, tituloPagina = "Gerenciamento de usuários", mostrarBack = false)
            card(classes = "flex flex-col gap-8") {
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
                    botao(tipo = TiposBotaoEnum.SUBTLE) {
                        icone(IconesEnum.ADICIONAR, usarPadding = false, size = 2f)
                        +"Adicionar usuário"
                    }
                }
                tabelaComHeadersFixos(
                    headers = arrayListOf("Nome do promotor", "Tipo de conta", "Último acesso", "Ações"),
                    linhas = usuarios.map { usuario ->
                        exibirUsuario(usuario)
                    },
                )
            }
        }
    }
}

private fun exibirUsuario(usuario: UsuarioMock): List<FlowContent.() -> Unit> {
    return listOf(
        { +usuario.nome },
        { +usuario.tipo },
        { +usuario.ultimoAcesso },
        {
            div(classes = "flex flex-row gap-2") {
                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = usuario.acoes.contains("Editar"))
                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = usuario.acoes.contains("Visualizar"))
            }
        }
    )
}

private class UsuarioMock(
    val nome: String,
    val tipo: String,
    val ultimoAcesso: String,
    val acoes: ArrayList<String> = arrayListOf("Editar", "Visualizar")
)