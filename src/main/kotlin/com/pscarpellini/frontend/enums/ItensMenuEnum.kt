package com.pscarpellini.frontend.enums

import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum
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
        pagina = PaginasRestritasEnum.INICIO,
    ),
    PROMOCOES(
        nome = "Promoções",
        icone = IconesEnum.CARRINHO,
        pagina = PaginasComissaoEnum.PROMOCOES,
    ),
    SALDOS_DOS_PROMOTORES(
        nome = "Saldos dos promotores",
        icone = IconesEnum.PRECO,
        pagina = PaginasComissaoEnum.SALDOS_DOS_PROMOTORES,
    ),
    RELATORIOS(
        nome = "Relatórios",
        icone = IconesEnum.DOCUMENTO,
        pagina = PaginasComissaoEnum.RELATORIOS,
    ),
    GERENCIAMENTO_DE_USUARIOS(
        nome = "Gerenciamento de usuários",
        icone = IconesEnum.USUARIOS,
        pagina = PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS,
    ),
    CONFIGURACOES_DO_APP(
        nome = "Configurações do app",
        icone = IconesEnum.APP,
        pagina = PaginasRestritasEnum.CONFIGURACOES_DO_APP,
    ),
    HISTORICO_DE_TRANSACOES(
        nome = "Histórico de transações",
        icone = IconesEnum.HISTORICO,
        pagina = PaginasComissaoEnum.HISTORICO_DE_TRANSACOES,
    ),
}