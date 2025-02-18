package com.pscarpellini.rotas.comissao

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.logados.saldos.includeSelectDePromotores
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleSelectPromotores(contasRepository: ContasRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    contasRepository.listarPromotores(clienteId = sessao.conta?.cliente?.id ?: -1).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar promotores")
            is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                includeSelectDePromotores(
                    nomeDoCampo = parameters["contaSaldo"],
                    label = parameters["label"],
                    hint = parameters["hint"],
                    isObrigatorio = parameters["isObrigatorio"].toBoolean(),
                    promotores = resposta.data ?: listOf(),
                    hxOnChangePath = parameters["hxOnChangePath"],
                    hxTarget = parameters["hxTarget"]
                )
            }
        }
    }
}
