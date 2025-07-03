package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.ClienteVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*
import kotlin.toString

fun FlowContent.exibirConfiguracoesDoApp(
    sessao: SessaoUsuarioVO,
    cliente: ClienteVO
) {
    div(classes = "flex flex-col gap-6") {
        attributes["id"] = "form-configuracoes-app"
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-row gap-8") {
                img (classes = "max-w-44 max-h-44 ${ArredondamentosEnum.MD}", src = "data:image/png;base64, ${cliente.logo}")
                div(classes = "flex grow flex-col w-full gap-2") {
                    div(classes = "flex flex-col w-full gap-2") {
                        h2(classes = "grow") { +"Dados da empresa" }
                        div(classes = "grid grid-cols-2 gap-6 w-full") {
                            linhaValor(titulo = "Nome da empresa", valor = cliente.nome)
                            linhaValor(titulo = "Contato para suporte", valor = cliente.email)
//                        linhaValor(titulo = "Serviços contratados", valor = sessao.conta?.nome ?: "")
                            linhaValor(titulo = "CNPJ", valor = cliente.cnpj)
                        }
                    }
                    div(classes = "flex flex-col w-full gap-2") {
                        h2(classes = "grow") { +"Tema do aplicativo" }
                        div(classes = "grid grid-cols-2 gap-6 w-full") {
                            linhaValor(titulo = "Logotipo da empresa", valor = cliente.email)
                        }
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            if (
                sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_CONFIGURACOES_DO_APP)
            ) {
                botao(
                    tipo = TiposBotaoEnum.NEUTRAL,
                    hxPath = CaminhosBaseEnum.EDITAR_CONFIGURACOES_DO_APP,
                    hxTarget = "conteudo-interno",
                    hxParams = mapOf("clientId" to cliente.id.toString()),
                    enabled = true,
                ) {
                    icone(icone = IconesEnum.EDITAR)
                    +"Editar"
                }
            }
        }
    }
}