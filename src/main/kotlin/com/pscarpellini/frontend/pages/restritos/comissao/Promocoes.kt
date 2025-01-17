package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoHX
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.rotas.FragmentsRestritosEnum
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div

fun FlowContent.promocoes(
    sessao: SessaoUsuarioVO
) {
    val idDaTabela = "tabela_de_promocoes-${System.currentTimeMillis()}"

    includeHeaderLogado(sessao = sessao)
    card(classes = "flex flex-col gap-8 h-full") {
        div(classes = "flex flex-row w-full items-center gap-4") {
            inputField(
                inputType = InputType.text,
                enabled = true,
                hint = "Pesquise pelo nome",
                nomeDoCampo = "busca",
                classes = "grow",
                useHx = true,
                hxPost = "buscar",
                hxTarget = idDaTabela,
                icone = IconesEnum.BUSCAR
            )
            botao(tipo = TiposBotaoEnum.NEUTRAL) {
                icone(IconesEnum.FILTRO, usarPadding = false, size = 1.4f)
                +"Filtro"
            }
            botaoHX(tipo = TiposBotaoEnum.SUBTLE, link = PaginasRestritasEnum.NOVO_USUARIO.caminho) {
                icone(IconesEnum.ADICIONAR, usarPadding = false, size = 1.4f)
                +"Adicionar usuário"
            }
        }
        autoLoaderFragment(
            id = idDaTabela,
            usarDiferenciadorId = false,
            path = FragmentsRestritosEnum.FRAGMENT_TABELA_PROMOCOES.path,
            classes = "w-full grow"
        )
    }
}