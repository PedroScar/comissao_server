package com.pscarpellini.frontend.fragments.logados.videos

import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.frontend.fragments.geral.tag.tag
import com.pscarpellini.models.vos.VideoVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

private val HEADERS = arrayListOf("Nome", "Habilitado", "Destaque", "")

fun FlowContent.includeTabelaDeVideos(
    videos: List<VideoVO>?
) {
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = (videos ?: arrayListOf()).map { video -> exibirLinhaVideo(video) },
        classes = "h-full w-full"
    )
}

private fun exibirLinhaVideo(video: VideoVO): List<FlowContent.() -> Unit> {
    val textoHabilitado = if (video.habilitado) "Sim" else "Não"
    val textoDestaque = if (video.destaque) "Sim" else "Não"
    val tagHabilitado = if (video.habilitado) TiposTagsEnum.POSITIVE else TiposTagsEnum.DANGER
    val tagDestaque = if (video.destaque) TiposTagsEnum.POSITIVE else TiposTagsEnum.DANGER

    return listOf(
        {
            div(classes = "flex flex-row items-center gap-4") {
                img(
                    classes = "aspect-square size-16 ${ArredondamentosEnum.MD}",
                    src = "data:image/png;base64, ${video.thumb}"
                )
                +video.titulo
            }
        },
        { tag(texto = textoHabilitado, tipo = tagHabilitado, isSecundaria = true) },
        { tag(texto = textoDestaque, tipo = tagDestaque, isSecundaria = true) },
        {
            botaoIcone(
                tipo = TiposBotaoEnum.TRANSPARENT,
                hxTarget = "conteudo-interno",
                hxPath = CaminhosComissaoEnum.EXIBIR_VIDEO.path,
                hxParams = mapOf("id_video" to video.id.toString()),
                icone = IconesEnum.OLHO_ABERTO,
            )
        }
    )
}