package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.promocoes.includeTabelaDePromocoes
import com.pscarpellini.frontend.fragments.logados.saldos.includeSelectDePromocoes
import com.pscarpellini.frontend.fragments.logados.saldos.includeSelectDePromotores
import com.pscarpellini.frontend.pages.restritos.comissao.includeFormNovaPromocao
import com.pscarpellini.frontend.pages.restritos.comissao.novaPromocao
import com.pscarpellini.frontend.pages.restritos.comissao.promocoes
import com.pscarpellini.frontend.pages.restritos.comissao.visualizarPromocao
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import kotlinx.html.label
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

suspend fun RoutingContext.handleSelectPromotores(contasRepository: ContasRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    contasRepository.listarPromotores(clienteId = sessao.conta?.cliente?.id ?: -1).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar promotores")
            is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                includeSelectDePromotores(
                    label = parameters["label"],
                    hint = parameters["hint"],
                    isObrigatorio = parameters["isObrigatorio"].toBoolean(),
                    promotores = resposta.data ?: listOf(),
                )
            }
        }
    }
}
