package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.repositories.interfaces.ContadoresDashboardViewRepository
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import com.pscarpellini.rotas.comissao.handleWidgetVisaoGeral
import com.pscarpellini.rotas.comissao.handleWidgetTransacoesRecentes
import com.pscarpellini.rotas.comissao.handleWidgetPromocoesMaisUtilizadas
import io.ktor.server.routing.*

fun Route.widgetsInicio(
    promocoesRepository: PromocoesRepository,
    extratosRepository: ExtratosRepository,
    contadoresDashboardViewRepository: ContadoresDashboardViewRepository
) {
    post(WidgetsInicioEnum.PROMOCOES_WIDGET.path) { handleWidgetPromocoesMaisUtilizadas(promocoesRepository) }
    post(WidgetsInicioEnum.CONTAGEM_PROMOCOES_WIDGET.path) { handleWidgetVisaoGeral(contadoresDashboardViewRepository) }
    post(WidgetsInicioEnum.HISTORICO_DE_TRANSACOES_WIDGET.path) { handleWidgetTransacoesRecentes(extratosRepository) }
}

enum class WidgetsInicioEnum(
    override val path: String
): IPaginaEnum {
//    WIDGETS DA TELA INICIAL
    PROMOCOES_WIDGET("/int/promocoes/widget"),
    CONTAGEM_PROMOCOES_WIDGET("/int/promocoes/widget/contagem"),
    HISTORICO_DE_TRANSACOES_WIDGET("/int/promocoes/widget/historico_de_transacoes"),
}