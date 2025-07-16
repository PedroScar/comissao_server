package com.pscarpellini.frontend.pages.restritos.base

import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.divider.divider
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.geral.spacer.spacer
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.*

fun FlowContent.meuPerfil(
    sessao: SessaoUsuarioVO
) {
    div(classes = "flex flex-col gap-6") {
//    formulario(id = "form-meu-perfil", classes = "flex flex-col gap-6", autoValidar = true) {
        card(classes = "flex flex-col") {
            includeContentGrid(
                colunas = 3,
                classes = "w-full"
            ) {
                div(classes = "row-span-2 flex flex-col items-end") {
                    h3(classes = "w-full") { +"Foto de perfil" }
                    div(classes = "flex flex-row gap-2") {
                        avatar(nome = sessao.conta?.nome ?: "", imagemUrl = sessao.conta?.imagemDePerfil, tipo = TiposAvatarEnum.EXTRA_LARGE_CIRCLE, classes = "grow")
                        span (classes = CoresEnum.LOW_LIGHT.text) {
                            +"Esta imagem é destinada apenas ao perfil interno e não será compartilhada externamente."
                        }
                    }
                }
                linhaValor(titulo = "Nome completo", valor = sessao.conta?.nome ?: "-")
                linhaValor(titulo = "Email", valor = sessao.conta?.email ?: "-")
                linhaValor(titulo = "CPF", valor = sessao.conta?.cpf ?: "-")
                linhaValor(titulo = "Telefone / Celular", valor = sessao.conta?.telefone ?: "-")

                div {
                    h3 { +"Senha" }
                    span(classes = CoresEnum.LOW_LIGHT.text) {
                        +"Proteja sua conta com uma senha exclusiva. Lembre-se, você pode atualizá-la sempre que necessário."
                    }
                }
                div {
                    attributes["id"] = "form-perfil-alterar-senha"
                    botao(
                        tipo = TiposBotaoEnum.NEUTRAL,
                        hxPath = CaminhosBaseEnum.ALTERAR_SENHA_PERFIL,
                        classes = "self-start",
                        hxTarget = "form-perfil-alterar-senha"
                    ) { +"Alterar senha" }
                }
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botao(
                tipo = TiposBotaoEnum.SUBTLE,
                hxPath = CaminhosBaseEnum.EDITAR_MEU_PERFIL,
                hxTarget = "form-meu-perfil"
            ) { +"Editar perfil" }
        }
    }
}