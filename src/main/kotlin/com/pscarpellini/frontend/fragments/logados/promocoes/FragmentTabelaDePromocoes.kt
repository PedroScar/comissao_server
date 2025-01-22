package com.pscarpellini.frontend.fragments.logados.promocoes

import com.pscarpellini.extensions.formatarIntervaloDeDatas
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoHX
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.PromocaoVO
import kotlinx.html.*

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
        { +promocao.status.nome }, // TODO: Incluir componente de tag
        { +"483" },
        {
            botao(tipo = TiposBotaoEnum.TRANSPARENT) { +"Abrir" }
//            div(classes = "flex flex-row gap-2") {
//                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Editar"))
//                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Visualizar"))
//            }
        }
    )
}