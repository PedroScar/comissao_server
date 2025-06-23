package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.promocoes.includeTabelaDePromocoes
import com.pscarpellini.frontend.fragments.logados.saldos.includeSelectDePromocoes
import com.pscarpellini.frontend.pages.restritos.comissao.*
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

suspend fun RoutingContext.handleFragmentTabelaPromocoes(promocoesRepository: PromocoesRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""

    val sessao = obterSessao()
    promocoesRepository.carregarPromocoes(termo = busca, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = "Credenciais inválidas, tente novamente."
            )

            is DbResponse.Successo -> {
                call.respondFragment { includeTabelaDePromocoes(promocoes = resposta.data) }
            }
        }
    }
}

suspend fun RoutingContext.handlePromocoes() {
    val sessao = obterSessao()
    sessao.paginaAtual = PaginasComissaoEnum.PROMOCOES
    call.respondFragment(HttpStatusCode.OK) {
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        promocoes()
    }
}

suspend fun RoutingContext.handleNovaPromocao() {
    val sessao = obterSessao()

    call.respondFragment(HttpStatusCode.OK) {
        sessao.paginaAtual = PaginasComissaoEnum.NOVA_PROMOCAO
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        novaPromocao()
    }
}

suspend fun RoutingContext.handleEditarPromocao(
    repository: PromocoesRepository
) {
    val sessao = obterSessao()

    val idPromocao = call.request.queryParameters["id_promocao"]?.toIntOrNull()
        ?: call.parameters["id_promocao"]?.toIntOrNull()
        ?: call.receiveParameters()["id_promocao"]?.toIntOrNull()
        ?: -1

    if (idPromocao == -1) {
        call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Ocorreu um erro ao buscar a promoção selecionada."
        )
        return
    }

    repository.carregarPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar a promoção."
            )

            is DbResponse.Successo -> {
                if (resposta.data == null) {
                    call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar a promoção."
                    )
                } else {
                    call.respondFragment(HttpStatusCode.OK) {
                        sessao.paginaAtual = PaginasComissaoEnum.EDITAR_PROMOCAO
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        editarPromocao(resposta.data)
                    }
                }
            }
        }
    }
}

suspend fun RoutingContext.handleExibirPromocao(
    promocoesRepository: PromocoesRepository
) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()

    runCatching { parameters["id_promocao"]?.toInt() ?: -1 }.onSuccess { idPromocao ->
        sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_PROMOCAO

        promocoesRepository.carregarPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id!!)
            .let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar a promoção selecionada"
                    )

                    is DbResponse.Successo -> {
                        call.respondFragment(HttpStatusCode.OK) {
                            includeMenuPrincipal(sessao)
                            includeHeaderLogado(sessao)
                            exibirPromocao(sessao, resposta.data)
                        }
                    }
                }
            }
    }.onFailure {
        call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Ocorreu um erro ao buscar a promoção selecionada"
        )
    }
}

suspend fun RoutingContext.handleFormularioCriarPromocao(
    promocoesRepository: PromocoesRepository
) {
    runCatching {
        val sessao = obterSessao()

        val multipart = call.receiveMultipart(formFieldLimit = 512_000L)

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

        if (nome.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "O nome da promoção deve estar preenchido"
        )

        if (dataDeInicio.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Preencha a data de início da promoção"
        )

        if (imagemDeExibicao.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Envie uma imagem para a promoção"
        )

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
            valorAnterior = valorAnterior.toDoubleOrNull() ?: 0.0,
            valorAtual = valorAtual.toDoubleOrNull() ?: 0.0,
            compras = 0,
            vendas = 0
        )

        promocoesRepository.criarPromocao(novaPromocao).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(
                    tipo = TiposToastEnum.ERROR,
                    mensagem = resposta.mensagem ?: "Ocorreu um erro ao criar a promoção"
                )

                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    novaPromocao()
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Promoção cadastrada com sucesso")
                }
            }
        }
    }.onFailure {
        call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = it.message ?: "Ocorreu um erro ao criar a promoção"
        )
    }
}

suspend fun RoutingContext.handleFormularioEditarPromocao(
    promocoesRepository: PromocoesRepository
) {
    runCatching {
        val sessao = obterSessao()

        val multipart = call.receiveMultipart(formFieldLimit = 512_000L)

        var promocaoId: Int? = null
        var nome = ""
        var descricao = ""
        var dataDeInicio = ""
        var dataDeEncerramento = ""
        var imagemDeExibicao = ""
        var precoDeExibicao = ""
        var valorAnterior = ""
        var valorAtual = ""
        var compras = 0
        var vendas = 0

        multipart.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    when (part.name) {
                        "promocaoId" -> promocaoId = part.value.toIntOrNull()
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

        promocoesRepository.carregarPromocao(
            promocaoId = promocaoId!!,
            clienteId = sessao.conta?.cliente?.id!!
        ).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> Unit
                is DbResponse.Successo -> {
                    resposta.data?.let {
                        if (imagemDeExibicao.isBlank()) {
                            imagemDeExibicao = it.imagem
                        }
                        vendas = it.vendas
                        compras = it.compras
                    }
                }
            }
        }

        if (nome.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "O nome da promoção deve estar preenchido"
        )

        if (dataDeInicio.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Preencha a data de início da promoção"
        )

        if (imagemDeExibicao.isEmpty()) call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Envie uma imagem para a promoção"
        )

        var possuiDuracaoIndeterminada = false

        val dataDeValidade = runCatching { LocalDate.parse(dataDeEncerramento).atTime(23, 59) }
            .onSuccess { possuiDuracaoIndeterminada = true }
            .getOrNull()
            ?: LocalDateTime.now().plusYears(100)

        val promocao = PromocaoVO(
            id = promocaoId,
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
            valorAnterior = valorAnterior.toDoubleOrNull() ?: 0.0,
            valorAtual = valorAtual.toDoubleOrNull() ?: 0.0,
            vendas = vendas,
            compras = compras
        )

        promocoesRepository.editarPromocao(promocao).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondToast(
                    tipo = TiposToastEnum.ERROR,
                    mensagem = resposta.mensagem ?: "Ocorreu um erro ao editar a promoção"
                )

                is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                    exibirPromocao(sessao, resposta.data!!)
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Promoção editada com sucesso")
                }
            }
        }
    }.onFailure {
        call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = it.message ?: "Ocorreu um erro ao editar a promoção"
        )
    }
}

suspend fun RoutingContext.handleEncerrarPromocao(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    runCatching { parameters["id_promocao"]?.toInt() ?: -1 }.onSuccess { idPromocao ->
        promocoesRepository.encerrarPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id ?: -1)
            .let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao encerrar a promoção"
                    )

                    is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                        exibirPromocao(sessao, resposta.data)
                        toast("Promoção encerrada com sucesso!")
                    }
                }
            }
    }
}

suspend fun RoutingContext.handleRemoverPromocao(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    runCatching { parameters["id_promocao"]?.toInt() ?: -1 }.onSuccess { idPromocao ->
        promocoesRepository.removerPromocao(promocaoId = idPromocao, clienteId = sessao.conta?.cliente?.id ?: -1)
            .let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ops... algo de errado aconteceu!"
                    )

                    is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                        sessao.paginaAtual = PaginasComissaoEnum.VIDEOS
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        promocoes()
                        toast("Promoção excluída com sucesso!")
                    }
                }
            }
    }
}

suspend fun RoutingContext.handleSelectPromocoesAtivas(promocoesRepository: PromocoesRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    promocoesRepository.listarPromocoesAtivas(clienteId = sessao.conta?.cliente?.id ?: -1).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar as promoções ativas"
            )

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
