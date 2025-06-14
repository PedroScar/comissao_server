package com.pscarpellini.frontend.fragments.logados.promocoes

import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.extensions.formatarIntervaloDeDatas
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.frontend.fragments.geral.tag.tag
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.img

private val HEADERS = arrayListOf("Nome da promoção", "Data de início e fim", "Status", "Vendas", "")

fun FlowContent.includeTabelaDePromocoes(
    promocoes: List<PromocaoVO>?
) {
    val listaPromocoes: List<PromocaoVO> = promocoes ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = listaPromocoes.map { promocao ->
            exibirLinhaPromocao(promocao)
        },
        classes = "h-full w-full"
    )
}

private fun exibirLinhaPromocao(promocao: PromocaoVO): List<FlowContent.() -> Unit> {
    return listOf(
        {
            div (classes = "flex flex-row items-center gap-4") {
                img (classes = "aspect-square size-16 ${ArredondamentosEnum.MD}", src = "data:image/png;base64, ${promocao.imagem}")
                +promocao.titulo
            }
        },
        { +formatarIntervaloDeDatas(promocao.dataDisponivel, promocao.dataValidade) },
        { tag(texto = promocao.status.nome, tipo = promocao.status.tipoTag, isSecundaria = true) },
        { +"0" },
        {
            val parametros = mapOf("id_promocao" to promocao.id.toString())
            botaoIcone(
                tipo = TiposBotaoEnum.TRANSPARENT,
                hxTarget = "conteudo-interno",
                hxPath = CaminhosComissaoEnum.EXIBIR_PROMOCAO.path,
                hxParams = parametros,
                icone = IconesEnum.OLHO_ABERTO,
            )
        }
    )
}