package com.pscarpellini.enums.comissao

import com.pscarpellini.interfaces.IPaginaEnum

enum class CaminhosComissaoEnum(
    override val path: String,
) : IPaginaEnum {
    PROMOCOES(path = "/int/promocoes"),
    VIDEOS(path = "/int/videos"),
    NOVA_PROMOCAO(path = "/int/nova_promocao"),
    EXIBIR_PROMOCAO(path = "/int/promocao/exibir"),
    EDITAR_PROMOCAO(path = "/int/promocao/editar"),
    FORMULARIO_NOVA_PROMOCAO(path = "/forms/promocoes/nova_promocao"),
    FORMULARIO_ENCERRAR_PROMOCAO(path = "/forms/promocoes/encerrar_promocao"),
    SELECT_PROMOCOES_ATIVAS(path = "/select/promocoes/ativas"),

    SALDOS_DOS_PROMOTORES(path = "/int/saldos"),
    SELECT_PROMOTORES(path = "/select/promotores"),
    FORMULARIO_ALTERAR_SALDO(path = "/forms/saldos/alterar"),

    RELATORIOS(path = "/int/relatorios"),

    HISTORICO_DE_TRANSACOES(path = "/int/historico"),
}