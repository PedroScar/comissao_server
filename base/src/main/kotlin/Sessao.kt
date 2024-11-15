import enums.ContaTipoEnum
import models.Conta

object Sessao {
    var idPai: Int? = null
    var tipo: ContaTipoEnum? = null
    var conta: Conta? = null
}