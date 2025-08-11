package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.models.vos.ContaESaldoVO
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div

fun FlowContent.usuarioExibir(conta: ContaVO, sessao: SessaoUsuarioVO) {
    includeFormExibirUsuario(conta, sessao)
}

fun FlowContent.includeFormExibirUsuario(conta: ContaVO, sessao: SessaoUsuarioVO) {
    div(classes = "flex flex-col gap-6") {
        includeContentGrid(linhas = 1, colunas = 1) {
            card(classes = "flex flex-col gap-8") {
                includeContentGrid(linhas = 2, colunas = 3, classes = "w-full") {
                    inputField(
                        label = "Nome completo",
                        inputType = InputType.text,
                        nomeDoCampo = "nome",
                        hint = "Digite o nome completo",
                        enabled = false,
                        valueInicial = conta.nome
                    )
                    inputField(
                        label = "Nome de usuário",
                        inputType = InputType.text,
                        nomeDoCampo = "usuario",
                        hint = "Digite um nome de usuário",
                        enabled = false,
                        valueInicial = conta.usuario
                    )
                    inputField(
                        label = "Senha",
                        inputType = InputType.password,
                        nomeDoCampo = "password",
                        hint = "********",
                        enabled = false,
                        valueInicial = conta.senha
                    )
                    inputField(
                        label = "E-mail",
                        inputType = InputType.email,
                        nomeDoCampo = "email",
                        hint = "exemplo@email.com",
                        enabled = false,
                        classes = "lowercase",
                        valueInicial = conta.email
                    )
                    inputField(
                        label = "Telefone (opcional)",
                        inputType = InputType.tel,
                        nomeDoCampo = "telefone",
                        hint = "(00) 00000-0000",
                        enabled = false,
                        valueInicial = conta.telefone
                    )
                    inputField(
                        label = "Tipo de conta",
                        inputType = InputType.text,
                        nomeDoCampo = "tipoConta",
                        enabled = false,
                        valueInicial = conta.tipoConta
                    )
                }
                includeCardDePerfis()
            }
        }

        div(classes = "self-end flex flex-row gap-2") {
            if (sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_USUARIO)) {
                val parametros = mapOf(
                    "nome" to conta.nome,
                    "usuario" to conta.usuario,
                    "senha" to conta.senha.toString(),
                    "email" to conta.email,
                    "telefone" to conta.telefone,
                    "tipo" to conta.tipoConta
                )
                botao(
                    tipo = TiposBotaoEnum.NEUTRAL,
                    hxPath = CaminhosBaseEnum.USUARIO_EDITAR,
                    hxTarget = "conteudo-interno",
                    hxParams = parametros,
                    enabled = true,
                ) {
                    icone(icone = IconesEnum.EDITAR)
                    +"Editar"
                }
            }
        }
    }
}
