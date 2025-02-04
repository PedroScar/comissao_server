package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.inputFileUpload
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.novaPromocao(
    sessao: SessaoUsuarioVO,
) {
    includeFormNovaPromocao()
}

fun FlowContent.includeFormNovaPromocao() {
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
                    div(classes = "flex flex-col w-full") {
                        label(classes = "${CoresEnum.LOW_PURE.text} block text-base font-semibold") { +"Imagem de exibição" }
                        span (classes = CoresEnum.LOW_LIGHT.text) {
                            +"Tamanho máximo do arquivo é de 500kb. Os tipos suportados são .jpg e .png"
                        }
                        inputFileUpload(classes = "self-start mt-4", nomeDoCampo = "imagem_de_exibicao") { +"Enviar imagem" }
                    }
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
                hxPath = CaminhosComissaoEnum.FORMULARIO_NOVA_PROMOCAO,
                hxTarget = "form-nova-promocao",
                hxSwap = "outerHTML",
                hxEncoding = "multipart/form-data",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}
