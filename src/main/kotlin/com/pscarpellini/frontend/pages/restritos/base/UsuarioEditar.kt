package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.models.vos.ContaVO
import kotlinx.html.FlowContent
import kotlinx.html.InputType
import kotlinx.html.div

fun FlowContent.usuarioEditar(
    sessao: SessaoUsuarioVO,
) {
    includeHeaderLogado(sessao = sessao)
    includeFormEditarUsuario(sessao = sessao)
}

fun FlowContent.includeFormEditarUsuario(sessao: SessaoUsuarioVO) {
    formulario(id = "form-editar-usuario", classes = "flex flex-col gap-6", autoValidar = true) {
        includeContentGrid(linhas = 1, colunas = 1) {
            card(classes = "flex flex-col gap-8") {
                includeContentGrid(linhas = 2, colunas = 3, classes = "w-full") {
                    inputField(
                        label = "Nome completo",
                        inputType = InputType.text,
                        hint = "Digite o nome completo",
                        isObrigatorio = true,
                        nomeDoCampo = "nome",
                        valueInicial = sessao.conta?.nome
                    )
                    inputField(
                        label = "Nome de usuário",
                        inputType = InputType.text,
                        nomeDoCampo = "usuario",
                        hint = "Digite um nome de usuário",
                        valueInicial = sessao.conta?.usuario
                    )
                    inputField(
                        label = "Senha",
                        inputType = InputType.password,
                        nomeDoCampo = "password",
                        hint = "********",
                        valueInicial = sessao.conta?.senha ?: ""
                    )
                    inputField(
                        label = "E-mail",
                        inputType = InputType.email,
                        hint = "exemplo@email.com",
                        isObrigatorio = true,
                        nomeDoCampo = "email",
                        classes = "lowercase",
                        valueInicial = sessao.conta?.email
                    )
                    inputField(
                        label = "Telefone (opcional)",
                        inputType = InputType.tel,
                        hint = "(00) 00000-0000",
                        nomeDoCampo = "telefone",
                        valueInicial = sessao.conta?.telefone
                    )
                    includeSelectDePerfis()
                }
                includeCardDePerfis()
            }
        }

        div(classes = "self-end flex flex-row gap-2") {
            botaoLink(tipo = TiposBotaoEnum.SUBTLE, link = "javascript:history.back()") { +"Cancelar" }
            botao(
                hxPath = PaginasRestritasEnum.FORMULARIO_EDITAR_USUARIO.caminho,
                hxTarget = "form-editar-usuario",
                hxSwap = "outerHTML",
                enabled = true,
            ) { +"Salvar" }
        }
    }
}
