package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.*
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.linha_valor.linhaValor
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.saldos.includePopupModificarSaldo
import com.pscarpellini.frontend.fragments.logados.saldos.includeTabelaDeSaldos
import com.pscarpellini.frontend.pages.restritos.comissao.saldosDosPromotores
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.NovoExtratoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.ExtratosRepository
import com.pscarpellini.repositories.interfaces.SaldosRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleFragmentTabelaSaldosDosPromotores(saldosRepository: SaldosRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""

    val sessao = obterSessao()
    saldosRepository.carregarSaldoContas(nome = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
            is DbResponse.Successo -> { call.respondFragment { includeTabelaDeSaldos(saldos = resposta.data) } }
        }
    }
}

suspend fun RoutingContext.handleSaldosDosPromotores() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        saldosDosPromotores()
    }
}

suspend fun RoutingContext.handlePopupModificarSaldo() {
    call.respondPopup { includePopupModificarSaldo() }
}

suspend fun RoutingContext.handlePopupModificarSaldoInfosPromotor(contasRepository: ContasRepository) {
    val parameters = call.receiveParameters()

    val promotor = parameters["promotor"] ?: ""

    val sessao = obterSessao()
    contasRepository.carregarPromotor(promotorId = promotor.toInt(), clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
            is DbResponse.Successo -> call.respondFragment {
                linhaValor(id = "popup_valor_cpf", titulo = "CPF", valor = resposta.data?.cpf, hxSwapOob = "outerHTML:#popup_valor_cpf")
                linhaValor(id = "popup_valor_saldo_atual", titulo = "Saldo atual", valor = resposta.data?.saldo?.formatarValorMonetario() ?: "Sem saldo", hxSwapOob = "outerHTML:#popup_valor_saldo_atual")
            }
        }
    }
}

suspend fun RoutingContext.handleAlterarSaldo(extratosRepository: ExtratosRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()

    val contaSaldo = parameters["promotor"] ?: ""
    val valor = parameters["valor"] ?: ""
    val promocao = parameters["promocao"]
    val isCredito = parameters["isCredito"] ?: "false"

    if (contaSaldo.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Você precisa selecionar um promotor")
    if (valor.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O extrato deve ter um valor")

    runCatching {
        extratosRepository.criarRegistroDeExtrato(
            NovoExtratoVO(
                idContaResponsavel = sessao.conta?.id ?: -1,
                idContaSaldo = contaSaldo.toInt(),
                promocao = promocao?.toIntOrNull(),
                valor = valor.toDouble(),
                isCredito = isCredito.toBoolean(),
            )
        )
    }
        .onSuccess { call.respondFragment(fecharPopupAberto = true) { toast(tipo = TiposToastEnum.SUCCESS, mensagem = if(isCredito.toBoolean()) "Saldo adicionado com sucesso!" else "Saldo removido com sucesso!") } }
        .onFailure {
            call.respondToast(tipo = TiposToastEnum.ERROR, if(it.message?.contains("Saldo insuficiente") == true) "Saldo insuficiente para a operação" else "Erro ao adicionar registro de extrato")
        }
}