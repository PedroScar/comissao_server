package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.promocoes.includeListaDePromocoesWidget
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.server.routing.*

fun Route.widgetsInicio(
    promocoesRepository: PromocoesRepository,
) {
    post(WidgetsInicioEnum.PROMOCOES_WIDGET.path) {
        val sessao = obterSessao()

        promocoesRepository.carregarPromocoes(clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao carregar promoções")
                is DbResponse.Successo -> { call.respondFragment { includeListaDePromocoesWidget(promocoes = resposta.data) } }
            }
        }
    }
    post(WidgetsInicioEnum.CONTAGEM_PROMOCOES_WIDGET.path) {
        val sessao = obterSessao()

        promocoesRepository.contagemDePromocoesAtivas(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Falha ao contar as promoções ativas")
                is DbResponse.Successo -> { call.respondFragment { +"Contagem de promoções: ${resposta.data}" } }
            }
        }
    }
}

enum class WidgetsInicioEnum(
    override val path: String
): IPaginaEnum {
//    WIDGETS DA TELA INICIAL
    PROMOCOES_WIDGET("/int/promocoes/widget"),
    CONTAGEM_PROMOCOES_WIDGET("/int/promocoes/widget/contagem"),
}