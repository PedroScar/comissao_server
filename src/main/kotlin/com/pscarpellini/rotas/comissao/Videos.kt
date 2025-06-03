package com.pscarpellini.rotas.comissao


import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.saldos.includeSelectDePromocoes
import com.pscarpellini.frontend.pages.restritos.comissao.includeFormNovaPromocao
import com.pscarpellini.frontend.pages.restritos.comissao.novaPromocao
import com.pscarpellini.frontend.pages.restritos.comissao.videos
import com.pscarpellini.frontend.pages.restritos.comissao.visualizarPromocao
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

suspend fun RoutingContext.handleFragmentTabelaVideos(promocoesRepository: PromocoesRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""
    val sessao = obterSessao()

//    promocoesRepository.carregarPromocoes(termo = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
//        when (resposta) {
//            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Credenciais inválidas, tente novamente.")
//            is DbResponse.Successo -> { call.respondFragment { includeTabelaDePromocoes(promocoes = resposta.data) } }
//        }
//    }
    handleVideos()
}

suspend fun RoutingContext.handleVideos() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasComissaoEnum.VIDEOS
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        videos()
    }
}

suspend fun RoutingContext.handleNovaVideo() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasComissaoEnum.NOVA_PROMOCAO
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        novaPromocao(sessao)
    }
}

suspend fun RoutingContext.handleExibirVideo(
    promocoesRepository: PromocoesRepository
) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()

    runCatching { parameters["id_promocao"]?.toInt() ?: -1 }.onSuccess { idPromocao ->
        sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_PROMOCAO

        promocoesRepository.carregarPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar a promoção selecionada")
                is DbResponse.Successo -> {
                    call.respondFragment(HttpStatusCode.OK) {
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        visualizarPromocao(sessao, resposta.data, idPromocao)
                    }
                }
            }
        }
    }.onFailure { call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Ocorreu um erro ao buscar a promoção selecionada") }
}

suspend fun RoutingContext.handleFormularioNovoVideo(
    promocoesRepository: PromocoesRepository
) {
    val sessao = obterSessao()

    val multipart = call.receiveMultipart()

    var nome = ""
    var descricao = ""
    var dataDeInicio = ""
    var dataDeEncerramento = ""
    var imagemDeExibicao = ""
    var precoDeExibicao = ""
    var valorAnterior = ""
    var valorAtual = ""

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "nome" -> nome = part.value
                    "descricao" -> descricao = part.value
                    "data_de_inicio" -> dataDeInicio = part.value
                    "data_de_encerramento" -> dataDeEncerramento = part.value
                    "preco_de_exibicao" -> precoDeExibicao = part.value
                    "valor_anterior" -> valorAnterior = part.value
                    "valor_atual" -> valorAtual = part.value
                }
            }
            is PartData.FileItem -> {
                val fileBytes = part.provider().toByteArray()
                imagemDeExibicao = Base64.getEncoder().encodeToString(fileBytes)
            }
            else -> Unit
        }
        part.dispose()
    }

    if (nome.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "O nome da promoção deve estar preenchido")
    if (dataDeInicio.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Preencha a data de início da promoção")
    if (imagemDeExibicao.isEmpty()) call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = "Envie uma imagem para a promoção")

    var possuiDuracaoIndeterminada = false
    val dataDeValidade = runCatching { LocalDate.parse(dataDeEncerramento).atTime(23, 59) }
        .onSuccess { possuiDuracaoIndeterminada = true }
        .getOrNull()
        ?: LocalDateTime.now().plusYears(100)

    val novaPromocao = PromocaoVO(
        clientId = sessao.conta?.cliente?.id ?: -1,
        titulo = nome,
        subtitulo = descricao,
        conteudo = "",
        imagem = imagemDeExibicao,
        dataValidade = dataDeValidade,
        dataCriacao = LocalDateTime.now(),
        dataDisponivel = LocalDate.parse(dataDeInicio).atTime(0, 0),
        duracaoIndeterminada = possuiDuracaoIndeterminada,
        exibirPreco = precoDeExibicao.isEmpty(), // TODO: Corrigir este campo
        valorAnterior = valorAnterior.toDouble(),
        valorAtual = valorAtual.toDouble(),
    )
    promocoesRepository.criarPromocao(novaPromocao)

    call.respondFragment(HttpStatusCode.OK) {
        includeFormNovaPromocao()
        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Promoção cadastrada com sucesso")
    }
}


suspend fun RoutingContext.handleEncerrarVideo(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
//    runCatching { parameters["id_promocao"]?.toInt() ?: -1 }.onSuccess { idPromocao ->
//        promocoesRepository.encerrarPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id ?: -1).let { resposta ->
//            when (resposta) {
//                is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ocorreu um erro ao encerrar a promoção")
//                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) { visualizarPromocao(sessao, resposta.data, idPromocao) }
//            }
//        }
//    }
}

suspend fun RoutingContext.handleSelectVideoAtivas(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    promocoesRepository.listarPromocoesAtivas(clienteId = sessao.conta?.cliente?.id ?: -1).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar as promoções ativas")
            is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                includeSelectDePromocoes(
                    nomeDoCampo = parameters["nomeDoCampo"],
                    label = parameters["label"],
                    hint = parameters["hint"],
                    isObrigatorio = parameters["isObrigatorio"].toBoolean(),
                    promocoes = resposta.data ?: listOf(),
                )
            }
        }
    }
}
