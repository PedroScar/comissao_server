package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.inputImage
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*
import kotlin.toString

fun FlowContent.editarConfiguracoesDoApp(
    sessao: SessaoUsuarioVO
) {
    val cliente = sessao.conta?.cliente

    formulario(id = "form-editar-configuracoes-app", classes = "flex flex-col gap-6", autoValidar = true) {
        input(InputType.hidden, name = "clienteId") {
            value = cliente?.id.toString()
        }

        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                div(classes = "flex flex-col w-full gap-2") {
                    h2(classes = "grow") { +"Dados da empresa" }
                    div(classes = "grid grid-cols-2 gap-6 w-full") {
                        inputField(
                            label = "Nome",
                            inputType = InputType.text,
                            hint = "Digite o nome da sua empresa",
                            isObrigatorio = true,
                            maxLength = 100,
                            nomeDoCampo = "nome",
                            valueInicial = cliente?.nome,
                            classes = "col-span-2"
                        )
                        inputField(
                            label = "Contato para suporte",
                            inputType = InputType.text,
                            hint = "Digite o endereço de e-mail para contato",
                            isObrigatorio = true,
                            maxLength = 100,
                            nomeDoCampo = "emailContato",
                            valueInicial = cliente?.email,
                            classes = "col-span-2"
                        )

                        inputField(
                            label = "CNPJ",
                            inputType = InputType.text,
                            hint = "Digite o CNPJ",
                            isObrigatorio = true,
                            maxLength = 100,
                            nomeDoCampo = "cnpj",
                            valueInicial = cliente?.cnpj,
                            classes = "col-span-2"
                        )
                    }
                }
                div(classes = "flex flex-col w-full gap-2") {
                    h2(classes = "grow") { +"Logo do aplicativo" }
                    div(classes = "grid grid-cols-2 gap-6 w-full") {
                        inputImage(
                            imagem64 = cliente?.logo ?: "",
                            nomeDoCampo = "logo"
                        )
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = CaminhosBaseEnum.FORMULARIO_EDITAR_CONFIGURACOES_APP,
                hxTarget = "form-editar-configuracoes-app",
                hxEncoding = "multipart/form-data",
                disabledElt = true,
                enabled = false,
            ) { +"Salvar" }
        }
    }
}