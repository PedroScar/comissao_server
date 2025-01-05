package com.pscarpellini.rotas

import com.pscarpellini.extensions.criarNomeDeUsuario
import com.pscarpellini.models.vos.SessaoUsuarioVO
import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.*
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeListaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.promocoes.includeListaDePromocoesWidget
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.frontend.pages.restritos.*
import com.pscarpellini.models.DbResponse
import com.pscarpellini.models.vos.ContaVO
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PerfisDeAcessoRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.html.*

fun Route.widgetsInicio(
    promocoesRepository: PromocoesRepository,
) {
    post(WidgetsInicioEnum.PROMOCOES_WIDGET.path) {
        val sessao = obterSessao()

        promocoesRepository.carregarPromocoes(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeListaDePromocoesWidget(promocoes = resposta.data) } }
            }
        }
    }
}

enum class WidgetsInicioEnum(
    override val path: String
): IPaginaEnum {
//    WIDGETS DA TELA INICIAL
    PROMOCOES_WIDGET("/int/promocoes/widget"),
}