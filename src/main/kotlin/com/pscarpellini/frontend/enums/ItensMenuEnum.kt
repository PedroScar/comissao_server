package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.produtos.base.CaminhosBaseEnum
import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
import com.pscarpellini.enums.produtos.comissao.CaminhosComissaoEnum
import com.pscarpellini.enums.produtos.comissao.PaginasComissaoEnum
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IItensMenuEnum
import com.pscarpellini.interfaces.IPaginaEnum

enum class ItensMenuEnum(
    override val nome: String,
    val icone: IconesEnum? = null,
    val pagina: IPaginaEnum,
) : IItensMenuEnum {
    INICIO(
        nome = "Início",
        icone = IconesEnum.HOME,
        pagina = CaminhosBaseEnum.INICIO,
    ),
    PROMOCOES(
        nome = "Promoções",
        icone = IconesEnum.CARRINHO,
        pagina = CaminhosComissaoEnum.PROMOCOES,
    ),
    SALDOS_DOS_PROMOTORES(
        nome = "Saldos dos promotores",
        icone = IconesEnum.PRECO,
        pagina = CaminhosComissaoEnum.SALDOS_DOS_PROMOTORES,
    ),
    RELATORIOS(
        nome = "Relatórios",
        icone = IconesEnum.DOCUMENTO,
        pagina = CaminhosComissaoEnum.RELATORIOS,
    ),
    GERENCIAMENTO_DE_USUARIOS(
        nome = "Gerenciamento de usuários",
        icone = IconesEnum.USUARIOS,
        pagina = CaminhosBaseEnum.GERENCIAMENTO_DE_USUARIOS,
    ),
    CONFIGURACOES_DO_APP(
        nome = "Configurações do app",
        icone = IconesEnum.APP,
        pagina = CaminhosBaseEnum.CONFIGURACOES_DO_APP,
    ),
    HISTORICO_DE_TRANSACOES(
        nome = "Histórico de transações",
        icone = IconesEnum.HISTORICO,
        pagina = CaminhosComissaoEnum.HISTORICO_DE_TRANSACOES,
    ),
}