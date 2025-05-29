package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.div

private val HEADERS = arrayListOf("Nome completo", "Nome de usuário", "Último acesso", "Ações")


fun FlowContent.includeTabelaDeUsuarios(
    contas: List<ContaVO>?,
    sessao: SessaoUsuarioVO
) {
    val usuarios: List<ContaVO> = contas ?: arrayListOf()
    tabelaComHeadersFixos(
        headers = HEADERS,
        linhas = usuarios.map { conta ->
            exibirLinhaUsuario(conta, sessao)
        },
        classes = "h-full w-full"
    )
}



private fun exibirLinhaUsuario(conta: ContaVO, sessao: SessaoUsuarioVO): List<FlowContent.() -> Unit> {
    return listOf(
        {
            div(classes = "flex items-center gap-2") {
                avatar(nome = conta.nome, imagemUrl = conta.imagemDePerfil, tipo = TiposAvatarEnum.SMALL_CIRCLE)
                +conta.nome
            }
        },
        { +conta.usuario },
        { +conta.email },
        {
            if (sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_USUARIO)) {
                val parametros = mapOf("id_promocao" to "TESTE")
                botao(
                    tipo = TiposBotaoEnum.TRANSPARENT,
                    hxTarget = "conteudo-interno",
                    hxPath = CaminhosComissaoEnum.EXIBIR_PROMOCAO.path,
                    hxParams = parametros,
                ) { +"Abrir" }
            }
        }
    )
}