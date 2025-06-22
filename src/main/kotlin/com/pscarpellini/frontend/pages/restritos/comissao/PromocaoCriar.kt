package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.inputImage
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div

fun FlowContent.novaPromocao() {
    formulario(id = "form-nova-promocao", classes = "flex flex-col gap-6", autoValidar = true) {
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                includeContentGrid(colunas = 2, classes = "w-full") {
                    inputField(
                        label = "Nome da promoção",
                        inputType = InputType.text,
                        hint = "Digite algo breve e chamativo",
                        isObrigatorio = true,
                        maxLength = 100,
                        nomeDoCampo = "nome",
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Descrição (opcional)",
                        inputType = InputType.text,
                        hint = "Detalhe melhor sua promoção",
                        maxLength = 255,
                        nomeDoCampo = "descricao",
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Data de início",
                        inputType = InputType.date,
                        hint = "Selecione a data de início",
                        isObrigatorio = true,
                        nomeDoCampo = "data_de_inicio"
                    )
                    inputField(
                        label = "Data de encerramento (opcional)",
                        inputType = InputType.date,
                        hint = "Selecione a data de encerramento",
                        nomeDoCampo = "data_de_encerramento"
                    )

                    inputImage(nomeDoCampo = "imagem_de_exibicao")

                    card(classes = "col-span-2", showBordas = true) {
                        includeContentGrid(colunas = 2, classes = "w-full") {
                            inputField(
                                label = "Preço de exibição",
                                inputType = InputType.text,
                                hint = "Dentro do app aparecerá o valor anterior de um produto seguido do valor atual",
                                nomeDoCampo = "preco_de_exibicao",
                                classes = "col-span-2"
                            )
                            inputField(
                                label = "Valor anterior",
                                inputType = InputType.number,
                                hint = "R$ 0,00",
                                nomeDoCampo = "valor_anterior"
                            )
                            inputField(
                                label = "Valor atual",
                                inputType = InputType.number,
                                hint = "R$ 0,00",
                                nomeDoCampo = "valor_atual"
                            )
                        }
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = CaminhosComissaoEnum.FORMULARIO_CRIAR_PROMOCAO,
                hxTarget = "form-nova-promocao",
                hxEncoding = "multipart/form-data",
                hxSwap = "none",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}
