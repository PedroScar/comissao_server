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
import kotlinx.html.*

fun FlowContent.includeFormNovoVideo() {
    formulario(id = "form-novo-video", classes = "flex flex-col gap-6", autoValidar = true) {
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                includeContentGrid(colunas = 2, classes = "w-full") {
                    inputField(
                        label = "Titulo para o video",
                        inputType = InputType.text,
                        hint = "Digite algo breve e chamativo",
                        isObrigatorio = true,
                        maxLength = 100,
                        nomeDoCampo = "titulo",
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Link",
                        inputType = InputType.text,
                        hint = "Link do video do youtube",
                        isObrigatorio = true,
                        maxLength = 255,
                        nomeDoCampo = "link",
                        classes = "col-span-2"
                    )
                    div(classes = "flex flex-col w-full") {
                        label(classes = "${CoresEnum.LOW_PURE.text} block text-base font-semibold") { +"Imagem da thumb" }
                        span (classes = CoresEnum.LOW_LIGHT.text) {
                            +"Tamanho máximo do arquivo é de 500kb. Os tipos suportados são .jpg e .png"
                        }
                        inputFileUpload(classes = "self-start mt-4", nomeDoCampo = "thumb") { +"Enviar thumb" }
                    }
                    div(classes = "flex items-center gap-2 col-span-2") {
                        input(InputType.checkBox, name = "isDestaque") {
                            id = "isDestaque"
                            name = "isDestaque"
                            classes = setOf("form-checkbox h-5 w-5 text-blue-600")
                        }
                        label(classes = "text-gray-700 text-base") { +"Destaque" }
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = CaminhosComissaoEnum.FORMULARIO_CRIAR_VIDEO,
                hxTarget = "form-novo-video",
                hxSwap = "outerHTML",
                hxEncoding = "multipart/form-data",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}
