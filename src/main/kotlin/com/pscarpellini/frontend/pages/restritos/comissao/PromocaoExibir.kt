package com.pscarpellini.frontend.pages.restritos.comissao

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.comissao.StatusPromocoesEnum
import com.pscarpellini.extensions.formatarData
import com.pscarpellini.extensions.formatarValorMonetario
import com.pscarpellini.frontend.enums.designsystem.ArredondamentosEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.fragments.geral.botoes.botao
import com.pscarpellini.frontend.fragments.geral.card.card
import com.pscarpellini.frontend.fragments.geral.icone.icone
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.geral.tag.tag
import com.pscarpellini.frontend.fragments.logados.content_grid.includeContentGrid
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.models.vos.SessaoUsuarioVO
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.img

fun FlowContent.exibirPromocao(
    sessao: SessaoUsuarioVO,
    promocao: PromocaoVO?
) {
    div(classes = "flex flex-col gap-6") {
        attributes["id"] = "visualizacao-promocao"
        card(classes = "flex flex-col gap-8") {
            includeContentGrid(colunas = 3, classes = "w-full") {
                img (classes = "row-span-4 w-full ${ArredondamentosEnum.MD}", src = "data:image/png;base64, ${promocao?.imagem}")
                div(classes = "flex flex-row col-span-2 items-center") {
                    h2(classes = "grow") { +(promocao?.titulo ?: "") }
                    tag(texto = promocao?.status?.nome ?: "", tipo = promocao?.status?.tipoTag ?: TiposTagsEnum.NEUTRAL)
                }
                linhaValor(classes = "col-span-2", titulo = "Descrição", valor = promocao?.subtitulo ?: "")
                linhaValor(titulo = "Data de início", valor = promocao?.dataDisponivel.formatarData())
                linhaValor(titulo = "Data de encerramento", valor = if(promocao?.dataValidade == null && promocao?.duracaoIndeterminada == true) "Duração indeterminada" else promocao?.dataValidade?.formatarData() ?: "-")
                linhaValor(titulo = "Preço de exibição anterior", valor = promocao?.valorAnterior?.formatarValorMonetario() ?: "-")
                linhaValor(titulo = "Preço de exibição atual", valor = promocao?.valorAtual?.formatarValorMonetario() ?: "-")
            }
        }
        div(classes = "self-end flex flex-row gap-2") {
            if(
                sessao.papeisDeAcesso.contains(PapeisDeAcessoEnum.EDITAR_PROMOCAO)
                && promocao?.status != StatusPromocoesEnum.ENCERRADA
                && promocao?.status != StatusPromocoesEnum.CANCELADA
            ) {
                botao(
                    tipo = TiposBotaoEnum.NEUTRAL,
                    hxPath = CaminhosComissaoEnum.EDITAR_PROMOCAO,
                    hxTarget = "conteudo-interno",
                    hxParams = mapOf(
                        "id_promocao" to promocao!!.id.toString(),
                        "clientId" to  promocao.clientId.toString()
                    ),
                    enabled = true,
                ) {
                    icone(icone = IconesEnum.EDITAR)
                    +"Editar"
                }
                botao(
                    tipo = TiposBotaoEnum.PRIMARY,
                    hxPath = CaminhosComissaoEnum.FORMULARIO_ENCERRAR_PROMOCAO,
                    hxParams = mapOf("id_promocao" to promocao.id.toString()),
                    enabled = true,
                ) {
                    if(promocao.status == StatusPromocoesEnum.ATIVA) +"Encerrar promoção"
                    else +"Cancelar promoção"
                }
            }
        }
    }
}
