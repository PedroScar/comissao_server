package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import com.pscarpellini.frontend.enums.ItensMenuEnum
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class SessaoUsuarioVO(
    var menuSelecionado: ItensMenuEnum = ItensMenuEnum.INICIO,
    var menusDisponiveis: ArrayList<ItensMenuEnum> = ArrayList(ItensMenuEnum.entries),
) {
    // Define o tempo de validade da sessão em minutos
    private var duracaoSessaoMinutos = 30L
    // Incremento do tempo de sessão
    private var incrementoDeSessao = 5L

    private var _dataDeAcesso: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

    var dataDeAcesso: String
        get() = _dataDeAcesso
        private set(value) {
            _dataDeAcesso = value
        }

    var cliente: ContaVO? = null
        set(value) {
            field = value
            dataDeAcesso = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        }

    // Data e hora de expiração da sessão
    @Serializable(with = LocalDateTimeSerializer::class)
    private var dataDeExpiracaoInterna = LocalDateTime.now().plusMinutes(duracaoSessaoMinutos)

    // Formata a data de expiração para ser acessada
    val dataDeExpiracao: String
        get() = dataDeExpiracaoInterna.format(DateTimeFormatter.ISO_DATE_TIME)

    // Verifica se a sessão ainda está ativa
    val isSessaoAtiva: Boolean
        get() = LocalDateTime.now().isBefore(dataDeExpiracaoInterna)

    // Incrementa a data de expiração adicionando minutos a partir de agora
    fun aumentarPrazoDeExpiracao() {
        dataDeExpiracaoInterna = LocalDateTime.now().plusMinutes(incrementoDeSessao)
    }
}
