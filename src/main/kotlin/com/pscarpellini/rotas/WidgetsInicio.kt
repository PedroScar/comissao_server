package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.promocoes.includeListaDePromocoesWidget
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.rotas.comissao.handleWidgetContagens
import com.pscarpellini.rotas.comissao.handleWidgetHistoricoDeTransacoes
import com.pscarpellini.rotas.comissao.handleWidgetPromocoes
import io.ktor.server.routing.*

fun Route.widgetsInicio(
    promocoesRepository: PromocoesRepository,
    extratosRepository: ExtratosRepository,
    contasRepository: ContasRepository
) {
    post(WidgetsInicioEnum.PROMOCOES_WIDGET.path) { handleWidgetPromocoes(promocoesRepository) }
    post(WidgetsInicioEnum.CONTAGEM_PROMOCOES_WIDGET.path) { handleWidgetContagens(promocoesRepository, extratosRepository, contasRepository) }
    post(WidgetsInicioEnum.HISTORICO_DE_TRANSACOES_WIDGET.path) { handleWidgetHistoricoDeTransacoes(extratosRepository) }
}

enum class WidgetsInicioEnum(
    override val path: String
): IPaginaEnum {
//    WIDGETS DA TELA INICIAL
    PROMOCOES_WIDGET("/int/promocoes/widget"),
    CONTAGEM_PROMOCOES_WIDGET("/int/promocoes/widget/contagem"),
    HISTORICO_DE_TRANSACOES_WIDGET("/int/promocoes/widget/historico_de_transacoes"),
}