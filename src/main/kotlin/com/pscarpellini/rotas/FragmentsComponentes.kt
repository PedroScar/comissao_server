package com.pscarpellini.rotas

import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.rotas.base.handleComponenteTag
import io.ktor.server.routing.*

fun Route.fragmentsComponentes() {
    post(FragmentsComponentesEnum.COMPONENTE_TAG_TOGGGLE.path) { handleComponenteTag() }
}

enum class FragmentsComponentesEnum(
    override val path: String,
) : IFragmentEnum {
    COMPONENTE_TAG_TOGGGLE("/comp/tagToggle"),
}