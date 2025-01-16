package com.pscarpellini.frontend.fragments.logados.promocoes

import com.pscarpellini.frontend.enums.CoresEnum
import com.pscarpellini.frontend.enums.IconesEnum
import com.pscarpellini.frontend.enums.PosicoesDropdownEnum
import com.pscarpellini.frontend.enums.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botaoHX
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownDivider
import com.pscarpellini.frontend.fragments.geral.dropdown.DropdownItem
import com.pscarpellini.frontend.fragments.geral.dropdown.dropdown
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.PaginasRestritasEnum
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
        { +promocao.titulo },
        { +promocao.dataValidade.toString() },
        { +"Ativa" },
        { +"483" },
        {
            botaoHX(tipo = TiposBotaoEnum.TRANSPARENT, link = "") { +"Abrir" }
//            div(classes = "flex flex-row gap-2") {
//                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Editar"))
//                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Visualizar"))
//            }
        }
    )
}