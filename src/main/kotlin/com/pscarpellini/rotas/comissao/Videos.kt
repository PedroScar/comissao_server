package com.pscarpellini.rotas.comissao

import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.videos.includeTabelaDeVideos
import com.pscarpellini.frontend.pages.restritos.comissao.criarVideo
import com.pscarpellini.frontend.pages.restritos.comissao.editarVideo
import com.pscarpellini.frontend.pages.restritos.comissao.exibirVideo
import com.pscarpellini.frontend.pages.restritos.comissao.videos
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.VideoVO
import com.pscarpellini.repositories.interfaces.VideosRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import java.util.*

suspend fun RoutingContext.handleFragmentTabelaVideos(videosRepository: VideosRepository) {
    val parameters = call.receiveParameters()

    val busca = parameters["busca"] ?: ""
    val sessao = obterSessao()

    videosRepository.carregarListaVideos(clienteId = sessao.conta?.cliente?.id!!, termo = busca).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = "Credenciais inválidas, tente novamente."
            )

            is DbResponse.Successo -> {
                call.respondFragment { includeTabelaDeVideos(videos = resposta.data) }
            }
        }
    }
    handleVideos()
}

suspend fun RoutingContext.handleVideos() {
    val sessao = obterSessao()

    call.respondFragment(HttpStatusCode.OK) {
        sessao.paginaAtual = PaginasComissaoEnum.VIDEOS
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        videos()
    }
}

suspend fun RoutingContext.handleCriarVideo() {
    val sessao = obterSessao()

    call.respondFragment(HttpStatusCode.OK) {
        sessao.paginaAtual = PaginasComissaoEnum.CRIAR_VIDEO
        includeMenuPrincipal(sessao)
        includeHeaderLogado(sessao)
        criarVideo()
    }
}

suspend fun RoutingContext.handleEditarVideo(
    videosRepository: VideosRepository
) {
    val sessao = obterSessao()

    val idVideo = call.request.queryParameters["id_video"]?.toIntOrNull()
        ?: call.parameters["id_video"]?.toIntOrNull()
        ?: call.receiveParameters()["id_video"]?.toIntOrNull()
        ?: -1

    if (idVideo == -1) {
        call.respondToast(
            tipo = TiposToastEnum.ERROR,
            mensagem = "Ocorreu um erro ao buscar o vídeo selecionado."
        )
        return
    }

    videosRepository.carregarVideo(videoId = idVideo, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o vídeo selecionado"
            )

            is DbResponse.Successo -> {
                if (resposta.data == null) {
                    call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o vídeo selecionado"
                    )
                } else {
                    call.respondFragment(HttpStatusCode.OK) {
                        sessao.paginaAtual = PaginasComissaoEnum.EDITAR_VIDEO
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        editarVideo(resposta.data)
                    }
                }
            }
        }
    }
}

suspend fun RoutingContext.handleFormularioEditarVideo(
    videosRepository: VideosRepository
) {
    val sessao = obterSessao()
    val multipart = call.receiveMultipart()

    var videoId: Int? = null
    var titulo = ""
    var link = ""
    var isDestaque = false
    var thumb = ""

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "videoId" -> videoId = part.value.toIntOrNull()
                    "titulo" -> titulo = part.value
                    "link" -> link = part.value
                    "isDestaque" -> isDestaque = part.value == "on"
                }
            }

            is PartData.FileItem -> {
                if (part.name == "thumb" && part.originalFileName != null && part.originalFileName!!.isNotBlank()) {
                    val fileBytes = part.provider().toByteArray()
                    thumb = Base64.getEncoder().encodeToString(fileBytes)
                }
            }

            else -> Unit
        }
        part.dispose()
    }

    videosRepository.carregarVideo(videoId = videoId!!, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> Unit
            is DbResponse.Successo -> {
                resposta.data?.thumb?.let {
                    if (thumb.isBlank()) {
                        thumb = it
                    }
                }
            }
        }
    }

    if (titulo.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "O título do vídeo precisa estar preenchido"
    )

    if (link.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Adicione o link do youtube"
    )

    if (thumb.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Envie uma imagem para a thumb"
    )

    val videoEditado = VideoVO(
        id = videoId,
        clientId = sessao.conta?.cliente?.id ?: -2,
        titulo = titulo,
        habilitado = true,
        destaque = isDestaque,
        thumb = thumb,
        video_id = link.pegarVideoId()
    )

    videosRepository.editarVideo(videoEditado).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> {
                call.respondFragment(HttpStatusCode.OK) {
                    videos()
                    toast(tipo = TiposToastEnum.ERROR, mensagem = resposta.mensagem ?: "Ops... algo errado aconteceu!")
                }
            }

            is DbResponse.Successo -> {
                call.respondFragment(HttpStatusCode.OK) {
                    sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_VIDEO
                    includeMenuPrincipal(sessao)
                    includeHeaderLogado(sessao)
                    exibirVideo(sessao, resposta.data!!)
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Video editado com sucesso")
                }
            }
        }
    }
}

suspend fun RoutingContext.handleExibirVideo(
    videosRepository: VideosRepository
) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()

    runCatching { parameters["id_video"]?.toInt() ?: -1 }
        .onFailure {
            call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = "Ocorreu um erro ao buscar o video selecionado: \n" + it.message
            )
        }
        .onSuccess { idVideo ->
            sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_VIDEO

            videosRepository.carregarVideo(videoId = idVideo, clienteId = sessao.conta?.cliente?.id!!).let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ocorreu um erro ao buscar o video selecionado"
                    )

                    is DbResponse.Successo -> {
                        call.respondFragment(HttpStatusCode.OK) {
                            sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_VIDEO
                            includeMenuPrincipal(sessao)
                            includeHeaderLogado(sessao)
                            exibirVideo(sessao, resposta.data!!)
                        }
                    }
                }
            }
        }
}

suspend fun RoutingContext.handleFormularioNovoVideo(
    videosRepository: VideosRepository
) {
    val sessao = obterSessao()

    val multipart = call.receiveMultipart(formFieldLimit = 512_000L)

    var titulo = ""
    var link = ""
    var isDestaque = false
    var thumb = ""

    multipart.forEachPart { part ->
        when (part) {
            is PartData.FormItem -> {
                when (part.name) {
                    "titulo" -> titulo = part.value
                    "link" -> link = part.value
                    "isDestaque" -> isDestaque = part.value == "on"
                }
            }

            is PartData.FileItem -> {
                val fileBytes = part.provider().toByteArray()
                thumb = Base64.getEncoder().encodeToString(fileBytes)
            }

            else -> Unit
        }
        part.dispose()
    }

    if (titulo.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "O título do vídeo precisa estar preenchido"
    )

    if (link.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Adicione o link do youtube"
    )

    if (thumb.isEmpty()) call.respondToast(
        tipo = TiposToastEnum.ERROR,
        mensagem = "Envie uma imagem para a thumb"
    )

    val novoVideo = VideoVO(
        clientId = sessao.conta?.cliente?.id ?: -1,
        titulo = titulo,
        habilitado = true,
        destaque = isDestaque,
        thumb = thumb,
        video_id = link.pegarVideoId()
    )

    videosRepository.criarVideo(novoVideo).let { resposta ->
        when (resposta) {
            is DbResponse.Erro -> call.respondToast(
                tipo = TiposToastEnum.ERROR,
                mensagem = resposta.mensagem ?: "Ops... algo de errado aconteceu!"
            )

            is DbResponse.Successo -> {
                call.respondFragment(HttpStatusCode.OK) {
                    sessao.paginaAtual = PaginasComissaoEnum.CRIAR_VIDEO
                    includeMenuPrincipal(sessao)
                    includeHeaderLogado(sessao)
                    criarVideo()
                    toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Video cadastrado com sucesso")
                }
            }
        }
    }
}

private fun String.pegarVideoId(): String = this.split('=').last()

suspend fun RoutingContext.handleDesabilitarVideo(videosRepository: VideosRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()
    runCatching { parameters["id_video"]?.toInt() ?: -1 }.onSuccess { idVideo ->
        videosRepository.desabilitarVideo(videoId = idVideo, clienteId = sessao.conta?.cliente?.id ?: -1)
            .let { resposta ->
                when (resposta) {
                    is DbResponse.Erro -> call.respondToast(
                        tipo = TiposToastEnum.ERROR,
                        mensagem = resposta.mensagem ?: "Ops... algo de errado aconteceu!"
                    )

                    is DbResponse.Successo -> call.respondFragment(HttpStatusCode.OK) {
                        sessao.paginaAtual = PaginasComissaoEnum.EXIBIR_VIDEO
                        includeMenuPrincipal(sessao)
                        includeHeaderLogado(sessao)
                        exibirVideo(sessao, resposta.data!!)
                        toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Vídeo desabilitado com sucesso!")
                    }
                }
            }
    }
}

suspend fun RoutingContext.handleRemoverVideo(videosRepository: VideosRepository) {
    val sessao = obterSessao()
    val parameters = call.receiveParameters()

    runCatching { parameters["id_video"]?.toInt() ?: -1 }
        .onFailure { println("ERRO handleRemoverVideo ID: ${it.message}") }
        .onSuccess { idVideo ->
            runCatching {
                videosRepository.removerVideo(videoId = idVideo, clienteId = sessao.conta?.cliente?.id ?: -1)
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
                                videos()
                                toast(tipo = TiposToastEnum.SUCCESS, mensagem = "Vídeo excluído com sucesso!")
                            }
                        }
                    }
            }.onFailure { println("ERRO videosRepository.removerVideo ID: ${it.message}") }
        }
}
