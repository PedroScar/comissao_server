package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.tag.tag
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.models.vos.VideoVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.img

fun FlowContent.visualizarVideo(
    sessao: SessaoUsuarioVO,
    video: VideoVO?,
) {
    val textoHabilitado = if (video?.habilitado == true) "Habilitado" else "Não habilitado"
    val textoDestaque = if (video?.destaque == true) "Destaque" else "Normal"
    val tagHabilitado = if (video?.habilitado == true) TiposTagsEnum.POSITIVE else TiposTagsEnum.DANGER
    val tagDestaque = if (video?.destaque == true) TiposTagsEnum.POSITIVE else TiposTagsEnum.DANGER

    div(classes = "flex flex-col gap-6") {
        attributes["id"] = "visualizacao-video"
        card(classes = "flex flex-col gap-8") {
            includeContentGrid(colunas = 3, classes = "w-full") {
                img(
                    classes = "row-span-4 w-full ${ArredondamentosEnum.MD}",
                    src = "data:image/png;base64, ${video?.thumb}"
                )
                div(classes = "flex flex-row col-span-2 items-center") {
                    h2(classes = "grow") { +(video?.titulo ?: "") }
                }
                div(classes = "flex flex-row col-span-2 items-center") {
                    tag(texto = textoHabilitado, tipo = tagHabilitado, isSecundaria = true)
                }
                div(classes = "flex flex-row col-span-2 items-center") {
                    tag(texto = textoDestaque, tipo = tagDestaque, isSecundaria = true)
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botao(
                tipo = TiposBotaoEnum.NEUTRAL,
                hxPath = CaminhosComissaoEnum.EDITAR_VIDEO,
                hxTarget = "conteudo-interno",
                hxParams = mapOf(
                    "id_video" to video!!.id.toString(),
                    "clientId" to  video.clientId.toString()
                ),
                hxSwap = "outerHTML",
                enabled = true,
            ) {
                icone(icone = IconesEnum.EDITAR)
                +"Editar"
            }

            if (
                sessao.papeisDeAcesso.any { it == PapeisDeAcessoEnum.EDITAR_VIDEO } && video.habilitado
            ) {
                botao(
                    tipo = TiposBotaoEnum.PRIMARY,
                    hxPath = CaminhosComissaoEnum.FORMULARIO_REMOVER_VIDEO,
                    hxParams = mapOf("id_video" to video.id.toString()),
                    hxTarget = "visualizacao-video",
                    hxSwap = "outerHTML",
                    enabled = true,
                ) {
                    icone(icone = IconesEnum.CLOSE)
                    +"Remover video"
                }
            }
        }
    }
}
