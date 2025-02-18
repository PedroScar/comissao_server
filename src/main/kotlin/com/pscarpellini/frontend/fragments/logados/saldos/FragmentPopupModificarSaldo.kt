package com.pscarpellini.frontend.fragments.logados.saldos

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.base.PerfisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.*
import com.pscarpellini.frontend.fragments.geral.auto_loader.autoLoaderFragment
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
import com.pscarpellini.rotas.FragmentsRestritosEnum
import com.pscarpellini.rotas.WidgetsInicioEnum
import io.ktor.server.util.*
import kotlinx.html.*

fun FlowContent.includePopupModificarSaldo(
) {
//    val targetInfosPromotor = "target_infos_promotor"
    popup(
        titulo = "Modificar saldo",
        nome = "popup_modificar_saldo",
    ) {
        formulario(id = "form-popup-modificar-saldo", autoValidar = true) {
            includeContentGrid(linhas = 2, colunas = 2, classes = "w-full") {
                autoLoaderFragment(
                    id = "select_promotor",
                    path = CaminhosComissaoEnum.SELECT_PROMOTORES.path,
                    textoLoading = "Buscando promotores",
                    classes = "col-span-2",
//                    hxSwap = "outerHTML",
                    hxParams = mapOf(
                        "nomeDoCampo" to "contaSaldo",
                        "label" to "Promotor",
                        "isObrigatorio" to true.toString(),
                        "hxOnChangePath" to FragmentsRestritosEnum.FRAGMENT_POPUP_MODIFICAR_SALDO_INFOS_PROMOTOR.path,
                        "hxTarget" to "popup_valor_cpf"
                    )
                )
                linhaValor(id = "popup_valor_cpf", titulo = "CPF", valor = "-")
                linhaValor(id = "popup_valor_saldo_atual", titulo = "Saldo atual", valor = "-")
                divider(classes = "col-span-2", useMargin = false)
                div(classes = "flex justify-center col-span-2 select-none") {
                    toggleComTexto(
                        nomeDoCampo = "isCredito",
                        checked = true,
                        conteudoEsquerdaChecked = {
                            icone(IconesEnum.ADICIONAR_CIRCULO, size = 2.2f, classes = "invert")
                            +"Adicionando saldo"
                        },
                        conteudoEsquerdaUnchecked = {
                            icone(IconesEnum.ADICIONAR_CIRCULO, size = 2.2f)
                            +"Adicionar saldo"
                        },
                        conteudoDireitaUnchecked = {
                            icone(IconesEnum.REMOVER_CIRCULO, size = 2.2f, classes = "invert")
                            +"Removendo saldo"
                        },
                        conteudoDireitaChecked = {
                            icone(IconesEnum.REMOVER_CIRCULO, size = 2.2f)
                            +"Remover saldo"
                        }
                    )
                }
                inputField(
                    label = "Valor",
                    inputType = InputType.number,
                    hint = "R$ 0,00",
                    isObrigatorio = true,
                    minValue = "0",
                    nomeDoCampo = "valor",
                    classes = "col-span-2"
                )
                autoLoaderFragment(
                    id = "select_promocoes_ativas",
                    path = CaminhosComissaoEnum.SELECT_PROMOCOES_ATIVAS.path,
                    textoLoading = "Buscando promoções ativas",
                    classes = "col-span-2",
                    hxParams = mapOf(
                        "nomeDoCampo" to "promocao",
                        "label" to "Promoção",
                        "hint" to "Nenhuma promoção selecionada",
                        "isObrigatorio" to false.toString(),
                    )
                )
            }
            div(classes = "flex flex-row justify-end gap-2 mt-4") {
                botao(
                    id = DefaultsIdsEnum.CLOSE_POPUP_BUTTON,
                    tipo = TiposBotaoEnum.SUBTLE,
                    isAutovalidateButton = false,
                    enabled = true,
                ) { +"Cancelar" }
                botao(
                    hxPath = CaminhosComissaoEnum.FORMULARIO_ALTERAR_SALDO,
                    hxTarget = "form-popup-modificar-saldo",
                    hxSwap = "outerHTML",
                ) { +"Salvar" }
            }
        }
    }
}