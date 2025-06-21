package com.pscarpellini.frontend.fragments.geral.auto_loader

import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.fragments.geral.loading.loading
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun FlowContent.autoLoaderFragment(
    id: String,
    path: String,
    usarDiferenciadorId: Boolean = true,
    isVerticalLoading: Boolean = false,
    textoLoading: String = "Carregando",
    hxParams: Map<String, String> = mapOf(),
    hxReplaceUrl: String? = null,
    hxSwap: String? = null,
    classes: String = "",
): String {
    val idDoConteudo = if (usarDiferenciadorId) "$id-${System.currentTimeMillis()}" else id
    val idDoLoading = "loading_$id"
    div(classes = "relative $classes") {
        attributes["hx-post"] = path
        attributes["hx-trigger"] = "load"
        attributes["hx-target"] = "#$idDoConteudo"
        if (hxSwap != null) attributes["hx-swap"] = hxSwap
        attributes["hx-indicator"] = "#$idDoLoading"
        attributes["hx-vals"] = Json.encodeToString(hxParams)
        hxReplaceUrl?.let { attributes["hx-replace-url"] = it }
        div(classes = "peer/$idDoLoading absolute inset-0 ${CoresEnum.HIGH_PURE.bg} bg-opacity-75 flex items-center justify-center z-10 collapse") {
            attributes["id"] = idDoLoading
            loading(id = idDoLoading, isVertical = isVerticalLoading, textoLoading = textoLoading)
        }
        div(classes = "w-full h-full peer-[.htmx-request]/$idDoLoading:collapse peer-[.htmx-request]/$idDoLoading:h-0") {
            attributes["id"] = idDoConteudo
        }
    }
    return idDoConteudo
}