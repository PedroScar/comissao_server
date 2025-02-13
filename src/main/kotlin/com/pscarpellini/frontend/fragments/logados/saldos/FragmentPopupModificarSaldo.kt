package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.botoes.botaoHX
import com.pscarpellini.frontend.fragments.geral.botoes.botaoIcone
import com.pscarpellini.frontend.fragments.geral.botoes.botaoLink
import com.pscarpellini.frontend.fragments.geral.conta.miniConta
import com.pscarpellini.frontend.fragments.geral.divider.divider
import com.pscarpellini.frontend.fragments.geral.formulario.formulario
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.inputs.inputField
import com.pscarpellini.frontend.fragments.geral.inputs.selectField
import com.pscarpellini.frontend.fragments.geral.inputs.toggleComTexto
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.geral.popup.popup
import com.pscarpellini.frontend.fragments.geral.tabela.tabelaComHeadersFixos
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.frontend.style.Colors
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.SaldoVO
import com.pscarpellini.rotas.FragmentsComponentesEnum
import io.ktor.server.util.*
import kotlinx.html.*

fun FlowContent.includePopupModificarSaldo(
) {
    popup(
        titulo = "Modificar saldo",
        nome = "popup_modificar_saldo",
    ) {
        formulario(id = "form-popup-modificar-saldo", autoValidar = true) {
            attributes["lm-popup-nome"] = "popup-modificar-saldo"
            includeContentGrid(linhas = 2, colunas = 2, classes = "w-full") {
                selectField(
                    label = "Promotor",
                    hint = "Ralph Edwards",
                    nomeDoCampo = "nomeDoPromotor",
                    isObrigatorio = true,
                    opcoes = PerfisDeAcessoEnum.obterPerfisDisponiveis().map { it.slug to it.nome },
                    classes = "col-span-2"
                )
                linhaValor(titulo = "Endereço", valor = "Rua blablabla", classes = "col-span-2")
                linhaValor(titulo = "CPF", valor = "321.654.987-01")
                linhaValor(titulo = "Saldo atual", valor = "R$ 184,00")
                divider(classes = "col-span-2", useMargin = false)
                div(classes = "flex justify-center col-span-2 select-none") {
                    toggleComTexto(nomeDoCampo = "isAdicionarSaldo", conteudoChecked = {
                        icone(IconesEnum.ADICIONAR_CIRCULO, size = 2.2f)
                        +"Adicionar saldo"
                    }, conteudoUnchecked = {
                        icone(IconesEnum.REMOVER_CIRCULO, size = 2.2f)
                        +"Remover saldo"
                    })
                }
                inputField(
                    label = "Valor",
                    inputType = InputType.number,
                    hint = "R$ 0,00",
                    isObrigatorio = true,
                    nomeDoCampo = "valor",
                    classes = "col-span-2"
                )
                selectField(
                    label = "Promoção",
                    hint = "Selecione uma promoção ativa",
                    nomeDoCampo = "promocao",
                    isObrigatorio = false,
                    opcoes = PerfisDeAcessoEnum.obterPerfisDisponiveis().map { it.slug to it.nome },
                    classes = "col-span-2"
                )
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            botao(
                id = DefaultsIdsEnum.CLOSE_POPUP_BUTTON,
                tipo = TiposBotaoEnum.SUBTLE,
                isAutovalidateButton = false,
                enabled = true,
            ) { +"Cancelar" }
            botao(
                hxPath = PaginasRestritasEnum.FORMULARIO_NOVO_USUARIO.caminho,
                hxTarget = "form-popup-modificar-saldo",
                hxSwap = "outerHTML",
                enabled = false,
            ) { +"Salvar" }
        }
    }
}