package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ContaVO
import kotlinx.html.FlowContent

private val HEADERS = arrayListOf("Nome do promotor", "Tipo de conta", "Último acesso", "Ações")


fun FlowContent.includeTabelaDeUsuarios(
    contas: List<ContaVO>?
) {
    val usuarios: List<ContaVO> = contas ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = usuarios.map { conta ->
            exibirLinhaUsuario(conta)
        },
        classes = "h-full w-full"
    )
}



private fun exibirLinhaUsuario(conta: ContaVO): List<FlowContent.() -> Unit> {
    return listOf(
        { +conta.nome },
        { +conta.usuario },
        { +conta.email },
        {
//            div(classes = "flex flex-row gap-2") {
//                botaoIcone(icone = IconesEnum.EDITAR, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Editar"))
//                botaoIcone(icone = IconesEnum.OLHO_ABERTO, tipo = TiposBotaoEnum.NEUTRAL, enabled = conta.acoes.contains("Visualizar"))
//            }
        }
    )
}