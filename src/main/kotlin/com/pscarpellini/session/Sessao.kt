package com.pscarpellini.session

import com.pscarpellini.enums.ContaTipoEnum
import com.pscarpellini.models.Conta

object Sessao {
    const val encaminhamentoWhatsapp = 5519993994583

    var idPai: Int? = null
    var tipo: ContaTipoEnum? = null
    var conta: Conta? = null
}