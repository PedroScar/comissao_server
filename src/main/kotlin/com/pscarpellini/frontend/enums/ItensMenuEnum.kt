package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.base.PapeisDeAcessoEnum
import com.pscarpellini.enums.base.CaminhosBaseEnum
import com.pscarpellini.enums.comissao.CaminhosComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import com.pscarpellini.interfaces.IPaginaEnum

enum class ItensMenuEnum(
    override val nome: String,
    val icone: IconesEnum? = null,
    val pagina: IPaginaEnum,
    val papelDeAcesso: PapeisDeAcessoEnum
) : IItensMenuEnum {
    INICIO(
        nome = "Início",
        icone = IconesEnum.HOME,
        pagina = CaminhosBaseEnum.INICIO,
        papelDeAcesso = PapeisDeAcessoEnum.INICIO
    ),
    PROMOCOES(
        nome = "Promoções",
        icone = IconesEnum.CARRINHO,
        pagina = CaminhosComissaoEnum.PROMOCOES,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_PROMOCOES
    ),
    VIDEOS(
        nome = "Videos",
        icone = IconesEnum.VIDEOS,
        pagina = CaminhosComissaoEnum.VIDEOS,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_VIDEOS
    ),
    SALDOS_DOS_PROMOTORES(
        nome = "Saldos dos promotores",
        icone = IconesEnum.PRECO,
        pagina = CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_SALDOS
    ),
    RELATORIOS(
        nome = "Relatórios",
        icone = IconesEnum.DOCUMENTO,
        pagina = CaminhosComissaoEnum.RELATORIOS,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_RELATORIOS
    ),
    GERENCIAMENTO_DE_USUARIOS(
        nome = "Gerenciamento de usuários",
        icone = IconesEnum.USUARIOS,
        pagina = CaminhosBaseEnum.GERENCIAMENTO_DE_USUARIOS,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_USUARIOS
    ),
    CONFIGURACOES_DO_APP(
        nome = "Configurações do app",
        icone = IconesEnum.APP,
        pagina = CaminhosBaseEnum.CONFIGURACOES_DO_APP,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_CONFIGURACOES_DO_APP
    ),
    HISTORICO_DE_TRANSACOES(
        nome = "Histórico de transações",
        icone = IconesEnum.HISTORICO,
        pagina = CaminhosComissaoEnum.HISTORICO_DE_TRANSACOES,
        papelDeAcesso = PapeisDeAcessoEnum.VISUALIZAR_HISTORICO_DE_TRANSACOES
    ),
}