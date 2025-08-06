package com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
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
                avatar(nome = conta.nome, imagemUrl = conta.imagemDePerfil, tipo = TiposAvatarEnum.MEDIUM_CIRCLE)
                +conta.nome
            }
        },
        { +conta.usuario },
        { +conta.email },
        {
            if (sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_USUARIO)) {
                val parametros = mapOf(
                    "conta_id" to conta.id.toString(),
                    "cliente_id" to conta.cliente?.id.toString()
                )
                botao(
                    tipo = TiposBotaoEnum.TRANSPARENT,
                    hxTarget = "conteudo-interno",
                    hxPath = CaminhosBaseEnum.USUARIO_EXIBIR.path,
                    hxParams = parametros,
                ) { +"Exibir" }
            }
        }
    )
}
