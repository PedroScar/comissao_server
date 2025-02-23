package com.pscarpellini.frontend.fragments.geral.conta

import com.pscarpellini.enums.base.PerfisDeAcessoEnum.Companion.obterEnumPeloSlug
import com.pscarpellini.frontend.enums.designsystem.AlinhamentosEnum
import com.pscarpellini.frontend.enums.designsystem.CoresEnum
import com.pscarpellini.frontend.enums.designsystem.TiposAvatarEnum
import com.pscarpellini.frontend.enums.designsystem.TiposBotaoEnum
import com.pscarpellini.frontend.fragments.geral.avatar.avatar
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.models.vos.ContaVO
import kotlinx.html.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun FlowContent.miniConta(
    conta: ContaVO,
    exibirPerfil: Boolean = true,
    classes: String = ""
) {
    div(classes = "items-center flex flex-row gap-2 $classes") {
        avatar(nome = conta.nome, imagemUrl = conta.imagemDePerfil, tipo = TiposAvatarEnum.SMALL_CIRCLE)
        div(classes = "flex flex-col") {
            span(classes = "${CoresEnum.LOW_MEDIUM.text}") { +conta.nome }
            if(exibirPerfil) span(classes = "${CoresEnum.LOW_LIGHT.text} text-sm") { +obterEnumPeloSlug(conta.tipoConta).nome }
        }
    }
}