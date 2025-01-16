package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.toast.toast
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

        promocoesRepository.carregarPromocoes(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeListaDePromocoesWidget(promocoes = resposta.data) } }
            }
        }
    }
}

enum class WidgetsInicioEnum(
    override val path: String
): IPaginaEnum {
//    WIDGETS DA TELA INICIAL
    PROMOCOES_WIDGET("/int/promocoes/widget"),
}