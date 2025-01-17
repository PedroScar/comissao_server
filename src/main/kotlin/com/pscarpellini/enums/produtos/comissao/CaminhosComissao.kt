package com.pscarpellini.enums.produtos.comissao

import com.pscarpellini.enums.PapeisDeAcessoEnum
import com.pscarpellini.enums.produtos.base.PaginasRestritasEnum.GERENCIAMENTO_DE_USUARIOS
import com.pscarpellini.frontend.enums.designsystem.IconesEnum
import com.pscarpellini.interfaces.IPaginaEnum
import com.pscarpellini.interfaces.ISublinksRestritosEnum

enum class CaminhosComissaoEnum(
    override val path: String,
) : IPaginaEnum {
    PROMOCOES(path = "/int/promocoes"),
    NOVA_PROMOCAO(path = "/int/promocoes"),
    EDITAR_PROMOCAO(path = "/int/promocoes"),

    SALDOS_DOS_PROMOTORES(path = "/int/saldos"),

    RELATORIOS(path = "/int/relatorios"),

    HISTORICO_DE_TRANSACOES(path = "/int/historico"),
}