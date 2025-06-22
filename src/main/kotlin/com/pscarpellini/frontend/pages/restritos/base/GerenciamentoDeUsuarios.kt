package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
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

fun FlowContent.gerenciamentoDeUsuarios(
    sessao: SessaoUsuarioVO
) {
    val idDaTabela = "tabela_de_usuarios-${System.currentTimeMillis()}"

    includeHeaderLogado(sessao = sessao)
    card(classes = "flex flex-col gap-8 grow mt-6") {
        div(classes = "flex flex-row w-full items-center gap-4") {
            inputField(
                inputType = InputType.text,
                enabled = true,
                hint = "Pesquise pelo nome",
                nomeDoCampo = "busca",
                classes = "grow",
                useHx = true,
                hxPost = FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path,
                hxTrigger = "keyup changed",
                hxTarget = idDaTabela,
                hxIndicator = "loading_$idDaTabela",
                icone = IconesEnum.BUSCAR
            )
//            botao(tipo = TiposBotaoEnum.NEUTRAL) {
//                icone(IconesEnum.FILTRO, usarPadding = false, size = 1.4f)
//                +"Filtro"
//            }
            botaoHX(tipo = TiposBotaoEnum.SUBTLE, link = CaminhosBaseEnum.NOVO_USUARIO) {
                icone(IconesEnum.ADICIONAR, usarPadding = false, size = 1.4f)
                +"Adicionar usuário"
            }
        }
        autoLoaderFragment(
            id = idDaTabela,
            usarDiferenciadorId = false,
            textoLoading = "Buscando usuários",
            path = FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path,
            classes = "w-full grow py-7"
        )
    }
}