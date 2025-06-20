package com.pscarpellini.rotas.base

import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.fragments.geral.tag.tag
import io.ktor.server.request.*
import io.ktor.server.routing.*

suspend fun RoutingContext.handleComponenteTag() {
    val parameters = call.receiveParameters()

    val tipo = parameters["tipo"] ?: ""
    val nome = parameters["nome"] ?: ""
    val classes = parameters["classes"] ?: ""
    val texto = parameters["texto"] ?: ""
    val isSecundaria = parameters["isSecundaria"]?.toBoolean() ?: false
    val exibirX = parameters["exibirX"]?.toBoolean() ?: false
    val isSelected = parameters["isSelected"]?.toBoolean() ?: false
    call.respondFragment { tag(
        tipo = TiposTagsEnum.obterEnumPeloNome(tipo),
        nome = nome,
        clicavel = true,
        classes = classes,
        texto = texto,
        isSecundaria = isSecundaria,
        exibirX = exibirX,
        isSelected = !isSelected,
    ) }
}