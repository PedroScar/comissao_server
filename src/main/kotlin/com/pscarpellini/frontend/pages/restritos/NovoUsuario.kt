package com.pscarpellini.frontend.pages.restritos

import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.PerfilDeAcessoVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.FragmentsRestritosEnum
import com.pscarpellini.rotas.PaginasRestritasEnum
import kotlinx.html.*

fun FlowContent.novoUsuario(
    sessao: SessaoUsuarioVO,
    perfisDeAcesso: List<PerfilDeAcessoVO>?
) {
    includeHeaderLogado(sessao = sessao, tituloPagina = "Novo usuário", mostrarBack = true)
    includeFormNovoUsuario()
//            includeFormNovoUsuario(perfisDeAcesso = perfisDeAcesso)
}

fun FlowContent.includeFormNovoUsuario(
//    perfisDeAcesso: List<PerfilDeAcessoVO>?
) {
    form(classes = "flex flex-col gap-6") {
        attributes["id"] = "form-novo-usuario"
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                div(classes = "grid grid-cols-2 grid-rows-2 gap-6 w-full") {
                    inputField(
                        label = "Nome completo",
                        inputType = InputType.text,
                        hint = "Digite o nome completo",
                        isObrigatorio = true,
                        nomeDoCampo = "nome"
                    )
                    inputField(
                        label = "E-mail",
                        inputType = InputType.email,
                        hint = "exemplo@email.com",
                        isObrigatorio = true,
                        nomeDoCampo = "email"
                    )
//                    selectField(
//                        label = "Tipo de conta",
//                        isObrigatorio = true,
//                        opcoes = perfisDeAcesso?.map { it.id.toString() to it.nome } ?: arrayListOf()
//                    )
                    //                        includeSelectDePerfis(perfisDeAcesso = perfisDeAcesso)
                    autoLoaderFragment(id = "select_perfil_de_acesso", path = FragmentsRestritosEnum.FRAGMENT_SELECT_PERFIS_DE_ACESSO.path)
                    inputField(
                        label = "Telefone (opcional)",
                        inputType = InputType.tel,
                        hint = "(00) 00000-0000",
                        nomeDoCampo = "telefone"
                    )
                }
//                div(classes = "${CoresEnum.BRAND_LIGHT.bg} ${ArredondamentosEnum.MD} py-4 px-6 w-full") {
//                    span { +"Tipos de contas:" }
//                    ul (classes = "list-disc ms-6") {
//                        perfisDeAcesso?.forEach {
//                            li {
//                                b { +"${it.nome}: " }
//                                +it.descricao
//                            }
//                        }
//                    }
//                }
                //                    includeCardDePerfis(perfisDeAcesso = perfisDeAcesso)
                autoLoaderFragment(id = "tipos_de_conta", path = FragmentsRestritosEnum.FRAGMENT_CARD_PERFIS_DE_ACESSO.path, classes = "w-full")
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = PaginasRestritasEnum.NOVO_USUARIO.path,
                hxTarget = "form-novo-usuario",
                hxSwap = "outerHTML"
            ) { +"Salvar" }
        }
    }
}
