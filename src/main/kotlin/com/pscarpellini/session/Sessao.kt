package com.pscarpellini.session

import com.pscarpellini.enums.ContaTipoEnum
import com.pscarpellini.model.Conta

object Sessao {
    var idPai: Int? = null
    var tipo: ContaTipoEnum? = null
    var conta: Conta? = null
}