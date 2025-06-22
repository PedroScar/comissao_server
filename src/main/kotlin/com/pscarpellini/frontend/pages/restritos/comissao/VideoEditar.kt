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
import com.pscarpellini.models.vos.VideoVO
import kotlinx.html.*

fun FlowContent.editarVideo(
    videoVO: VideoVO
) {
    formulario(id = "form-editar-video", classes = "flex flex-col gap-6", autoValidar = true) {
        input(InputType.hidden, name = "videoId") {
            value = videoVO.id.toString()
        }

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
                        valueInicial = videoVO.titulo,
                        classes = "col-span-2"
                    )
                    inputField(
                        label = "Link",
                        inputType = InputType.text,
                        hint = "Link do video do youtube",
                        isObrigatorio = true,
                        maxLength = 255,
                        nomeDoCampo = "link",
                        valueInicial = "https://www.youtube.com/watch?v=${videoVO.video_id}",
                        classes = "col-span-2"
                    )

                    inputImage(
                        imagem64 = videoVO.thumb,
                        nomeDoCampo = "imagem_de_exibicao"
                    )

                    div(classes = "flex items-center gap-2 col-span-2") {
                        input(InputType.checkBox, name = "isDestaque") {
                            id = "isDestaque"
                            name = "isDestaque"
                            classes = setOf("form-checkbox h-5 w-5 text-blue-600")
                            if (videoVO.destaque) {
                                checked = true
                            }
                        }
                        label(classes = "text-gray-700 text-base") { +"Destaque" }
                    }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = CaminhosComissaoEnum.FORMULARIO_EDITAR_VIDEO,
                hxTarget = "form-editar-video",
                hxEncoding = "multipart/form-data",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}
