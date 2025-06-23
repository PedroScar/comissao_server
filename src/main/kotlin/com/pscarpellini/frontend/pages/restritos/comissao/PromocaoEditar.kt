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
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div
import kotlinx.html.input

fun FlowContent.editarPromocao(
    promocao: PromocaoVO
) {
    formulario(id = "form-editar-promocao", classes = "flex flex-col gap-6", autoValidar = true) {
        input(InputType.hidden, name = "promocaoId") {
            value = promocao.id.toString()
        }

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
                        valueInicial = promocao.titulo,
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Descrição (opcional)",
                        inputType = InputType.text,
                        hint = "Detalhe melhor sua promoção",
                        maxLength = 255,
                        nomeDoCampo = "descricao",
                        valueInicial = promocao.subtitulo,
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Data de início",
                        inputType = InputType.date,
                        hint = "Selecione a data de início",
                        isObrigatorio = true,
                        nomeDoCampo = "data_de_inicio",
                        valueInicial = promocao.dataDisponivel.toLocalDate().toString()
                    )
                    inputField(
                        label = "Data de encerramento (opcional)",
                        inputType = InputType.date,
                        hint = "Selecione a data de encerramento",
                        nomeDoCampo = "data_de_encerramento",
                        valueInicial = promocao.dataValidade?.toLocalDate()?.toString() ?: ""
                    )
                    inputImage(
                        imagem64 = promocao.imagem,
                        nomeDoCampo = "imagem_de_exibicao"
                    )
                    card(classes = "col-span-2", showBordas = true) {
                        includeContentGrid(colunas = 2, classes = "w-full") {
                            inputField(
                                label = "Preço de exibição",
                                inputType = InputType.text,
                                hint = "Dentro do app aparecerá o valor anterior de um produto seguido do valor atual",
                                nomeDoCampo = "preco_de_exibicao",
                                valueInicial = promocao.conteudo,
                                classes = "col-span-2"
                            )
                            inputField(
                                label = "Valor anterior",
                                inputType = InputType.number,
                                hint = "R$ 0,00",
                                nomeDoCampo = "valor_anterior",
                                valueInicial = promocao.valorAnterior?.toString() ?: ""
                            )
                            inputField(
                                label = "Valor atual",
                                inputType = InputType.number,
                                hint = "R$ 0,00",
                                nomeDoCampo = "valor_atual",
                                valueInicial = promocao.valorAtual?.toString() ?: ""
                            )
                        }
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = CaminhosComissaoEnum.FORMULARIO_EDITAR_PROMOCAO,
                hxTarget = "form-editar-promocao",
                hxEncoding = "multipart/form-data",
                disabledElt = true,
                enabled = false,
            ) { +"Salvar" }
        }
    }
}
