package com.pscarpellini.enums.comissao

import com.pscarpellini.interfaces.IPaginaEnum

enum class CaminhosComissaoEnum(
    override val path: String,
) : IPaginaEnum {
    PROMOCOES(path = "/int/promocoes"),
    CRIAR_PROMOCAO(path = "/int/nova_promocao"),
    EXIBIR_PROMOCAO(path = "/int/promocao/exibir"),
    EDITAR_PROMOCAO(path = "/int/promocao/editar"),
    FORMULARIO_CRIAR_PROMOCAO(path = "/forms/promocoes/nova_promocao"),
    FORMULARIO_EDITAR_PROMOCAO(path = "/forms/promocoes/editar_promocao"),
    FORMULARIO_ENCERRAR_PROMOCAO(path = "/forms/promocoes/encerrar_promocao"),
    SELECT_PROMOCOES_ATIVAS(path = "/select/promocoes/ativas"),

    VIDEOS(path = "/int/videos"),
    CRIAR_VIDEO(path = "/int/criar_video"),
    EXIBIR_VIDEO(path = "/int/video/exibir"),
    EDITAR_VIDEO(path = "/int/video/editar"),
    FORMULARIO_CRIAR_VIDEO(path = "/forms/videos/criar_video"),
    FORMULARIO_EDITAR_VIDEO(path = "/forms/videos/editar_video"),
    FORMULARIO_HABILITAR_VIDEO(path = "/forms/videos/habilitar_video"),
    FORMULARIO_DESABILITAR_VIDEO(path = "/forms/videos/desabilitar_video"),
    FORMULARIO_REMOVER_VIDEO(path = "/forms/videos/remover_video"),
    SELECT_VIDEOS_ATIVOS(path = "/select/videos/ativas"),

    SALDOS_DOS_PROMOTORES(path = "/int/saldos"),
    SELECT_PROMOTORES(path = "/select/promotores"),
    FORMULARIO_ALTERAR_SALDO(path = "/forms/saldos/alterar"),

    RELATORIOS(path = "/int/relatorios"),

    HISTORICO_DE_TRANSACOES(path = "/int/historico"),
}