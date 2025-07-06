package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.inputFileUpload
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.editarMeuPerfil(
    sessao: SessaoUsuarioVO
) {
    includeHeaderLogado(sessao = sessao)
    formulario(id = "form-meu-perfil", classes = "flex flex-col gap-6", autoValidar = true) {
        includeContentGrid(
            linhas = 1,
            colunas = 1,
        ) {
            card(classes = "flex flex-col gap-8") {
                includeContentGrid(
                    linhas = 2,
                    colunas = 3,
                    classes = "w-full"
                ) {
                    div(classes = "row-span-2 flex flex-col items-end") {
                        div(classes = "flex flex-row gap-2") {
                            avatar(nome = sessao.conta?.nome ?: "", imagemUrl = sessao.conta?.imagemDePerfil, tipo = TiposAvatarEnum.EXTRA_LARGE_CIRCLE, classes = "grow")
                            span (classes = CoresEnum.LOW_LIGHT.text) {
                                +"Esta imagem é destinada apenas ao perfil interno e não será compartilhada externamente (o arquivo deve ter menos de 10MB)."
                            }
                        }
                        inputFileUpload(classes = "self-start mt-4", nomeDoCampo = "imagem_de_perfil") { +"Enviar imagem" }
                        botao(tipo = TiposBotaoEnum.WARNING_SUBTLE) { +"Remover imagem" }
                    }
                    linhaValor(titulo = "Nome completo", valor = sessao.conta?.nome ?: "-")
                    linhaValor(titulo = "Email", valor = sessao.conta?.email ?: "-")
                    linhaValor(titulo = "CPF", valor = sessao.conta?.cpf ?: "-")
                    inputField(
                        label = "Telefone / Celular",
                        inputType = InputType.text,
                        hint = "Digite seu número de telefone / celular",
                        isObrigatorio = false,
                        maxLength = 20,
                        valueInicial = sessao.conta?.telefone ?: "",
                        nomeDoCampo = "telefone"
                    )
                }

//                div(classes = "flex flex-col gap-4 w-full") {
//                    h2 { +"Senha" }
//                    span (classes = CoresEnum.LOW_LIGHT.text) {
//                        +"Proteja sua conta com uma senha exclusiva. Lembre-se, você pode atualizá-la"
//                        br
//                        +"sempre que necessário."
//                    }
//                    botaoLink(link = "#", tipo = TiposBotaoEnum.NEUTRAL, classes = "self-start") { +"Alterar senha" }
//                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botao(
                tipo = TiposBotaoEnum.SUBTLE,
                hxPath = CaminhosBaseEnum.MEU_PERFIL,
                hxTarget = "form-meu-perfil"
            ) { +"Cancelar" }
            botao(
                hxPath = CaminhosBaseEnum.FORMULARIO_EDITAR_MEU_PERFIL,
                hxTarget = "form-meu-perfil",
                hxSwap = "outerHTML",
                hxEncoding = "multipart/form-data",
            ) { +"Salvar" }
        }
    }
}