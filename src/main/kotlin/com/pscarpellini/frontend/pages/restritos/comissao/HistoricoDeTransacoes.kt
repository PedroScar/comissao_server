package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.rotas.FragmentsRestritosEnum
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div

fun FlowContent.historicoDeTransacoes() {
    val idDaTabela = "historico_de_transacoes-${System.currentTimeMillis()}"

    card(classes = "flex flex-col gap-8 grow") {
        div(classes = "flex flex-row w-full items-center gap-4") {
            inputField(
                inputType = InputType.text,
                enabled = true,
                hint = "Pesquise pelo nome",
                nomeDoCampo = "busca",
                classes = "grow",
                useHx = true,
                hxPost = FragmentsRestritosEnum.FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES.path,
                hxTrigger = "keyup changed",
                hxTarget = idDaTabela,
                hxIndicator = "loading_$idDaTabela",
                icone = IconesEnum.BUSCAR
            )
            botao(tipo = TiposBotaoEnum.NEUTRAL) {
                icone(IconesEnum.FILTRO, usarPadding = false, size = 1.4f)
                +"Filtro"
            }
        }
        autoLoaderFragment(
            id = idDaTabela,
            usarDiferenciadorId = false,
            path = FragmentsRestritosEnum.FRAGMENT_TABELA_HISTORICO_DE_TRANSACOES.path,
            classes = "w-full grow"
        )
    }
}