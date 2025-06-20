package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.configuracoesDoApp(
    sessao: SessaoUsuarioVO
) {
    formulario(id = "form-configuracoes-app", classes = "flex flex-col gap-6", autoValidar = true) {
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                div(classes = "flex flex-col w-full gap-2") {
                    h2(classes = "grow") { +"Dados da empresa" }
                    div(classes = "grid grid-cols-2 gap-6 w-full") {
                        linhaValor(titulo = "Nome da empresa", valor = sessao.conta?.nome ?: "")
                        linhaValor(titulo = "Contato para suporte", valor = sessao.conta?.email ?: "")
                        linhaValor(titulo = "Serviços contratados", valor = sessao.conta?.nome ?: "")
                        linhaValor(titulo = "CNPJ", valor = sessao.conta?.email ?: "")
                    }
                }
                div(classes = "flex flex-col w-full gap-2") {
                    h2(classes = "grow") { +"Tema do aplicativo" }
                    div(classes = "grid grid-cols-2 gap-6 w-full") {
                        linhaValor(titulo = "Logotipo da empresa", valor = sessao.conta?.email ?: "")
                        linhaValor(titulo = "Contato para suporte", valor = sessao.conta?.email ?: "")
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botao(
                hxPath = CaminhosComissaoEnum.FORMULARIO_CRIAR_PROMOCAO,
                hxTarget = "form-configuracoes-app",
                hxSwap = "outerHTML",
                hxEncoding = "multipart/form-data",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}