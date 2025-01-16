package com.pscarpellini.rotas

import com.pscarpellini.extensions.obterSessao
import com.pscarpellini.extensions.respondFragment
import com.pscarpellini.frontend.enums.TiposToastEnum
import com.pscarpellini.frontend.fragments.geral.toast.toast
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeCardDePerfis
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeTabelaDeUsuarios
import com.pscarpellini.frontend.fragments.logados.gerenciamento_de_usuarios.includeSelectDePerfis
import com.pscarpellini.interfaces.IFragmentEnum
import com.pscarpellini.frontend.fragments.logados.menu_principal.includeMenuPrincipal
import com.pscarpellini.frontend.fragments.logados.promocoes.includeTabelaDePromocoes
import com.pscarpellini.frontend.pages.restritos.promocoes
import com.pscarpellini.models.DbResponse
import com.pscarpellini.repositories.interfaces.ContasRepository
import com.pscarpellini.repositories.interfaces.PerfisDeAcessoRepository
import com.pscarpellini.repositories.interfaces.PromocoesRepository
import io.ktor.http.*
import io.ktor.server.routing.*

fun Route.fragmentsRestritos(
    contasRepository: ContasRepository,
    promocoesRepository: PromocoesRepository,
    perfisDeAcessoRepository: PerfisDeAcessoRepository,
) {
    post(FragmentsRestritosEnum.FRAGMENT_TABELA_PROMOCOES.path) {
        val sessao = obterSessao()
        promocoesRepository.carregarPromocoes(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeTabelaDePromocoes(promocoes = resposta.data) } }
            }
        }
    }

    post(FragmentsRestritosEnum.FRAGMENT_TABELA_USUARIOS.path) {
        val sessao = obterSessao()
        contasRepository.carregarUsuarios(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Credenciais inválidas, tente novamente.", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> { call.respondFragment { includeTabelaDeUsuarios(contas = resposta.data) } }
            }
        }
    }

    post(FragmentsRestritosEnum.FRAGMENT_MENU.path) {
        val sessao = obterSessao()
        call.respondFragment (HttpStatusCode.OK) {
            includeMenuPrincipal(sessao)
        }
    }

    post(FragmentsRestritosEnum.FRAGMENT_SELECT_PERFIS_DE_ACESSO.path) {
        val sessao = obterSessao()
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment { includeSelectDePerfis(perfisDeAcesso = resposta.data) }
            }
        }
    }
    post(FragmentsRestritosEnum.FRAGMENT_CARD_PERFIS_DE_ACESSO.path) {
        val sessao = obterSessao()
        perfisDeAcessoRepository.carregarPerfis(sessao.conta?.cliente?.id!!).let { resposta ->
            when (resposta) {
                is DbResponse.Erro -> call.respondFragment { toast("Falha ao buscar perfis de acesso", tipo = TiposToastEnum.ALERT) }
                is DbResponse.Successo -> call.respondFragment { includeCardDePerfis(perfisDeAcesso = resposta.data) }
            }
        }
    }
}

enum class FragmentsRestritosEnum(
    override val path: String,
): IFragmentEnum {
    //    FRAGMENTS ISOLADOS
    FRAGMENT_MENU("/int/fragment/menu_principal"),

    FRAGMENT_TABELA_PROMOCOES("/int/fragment/promocoes"),
    FRAGMENT_TABELA_USUARIOS("/int/fragment/tabela_usuarios"),

    FRAGMENT_SELECT_PERFIS_DE_ACESSO("/int/fragment/select_perfis_de_acesso"),
    FRAGMENT_CARD_PERFIS_DE_ACESSO("/int/fragment/card_perfis_de_acesso"),
}