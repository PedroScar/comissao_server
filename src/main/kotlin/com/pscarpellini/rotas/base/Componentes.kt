package com.pscarpellini.rotas.base

import com.pscarpellini.enums.base.PaginasRestritasEnum
import com.pscarpellini.enums.comissao.PaginasComissaoEnum
import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.extensions.respondToast
import com.pscarpellini.frontend.enums.ItensMenuEnum
import com.pscarpellini.frontend.enums.designsystem.TiposTagsEnum
import com.pscarpellini.frontend.enums.designsystem.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.tag.tag
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.header_logado.includeHeaderLogado
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.pages.restritos.base.gerenciamentoDeUsuarios
import com.pscarpellini.frontend.pages.restritos.base.includeFormNovoUsuario
import com.pscarpellini.frontend.pages.restritos.base.meuPerfil
import com.pscarpellini.frontend.pages.restritos.base.novoUsuario
import com.pscarpellini.frontend.pages.restritos.comissao.includeFormNovaPromocao
import com.pscarpellini.frontend.pages.restritos.comissao.novaPromocao
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.models.vos.PromocaoVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.utils.io.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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