package com.pscarpellini.models.vos

import com.pscarpellini.database.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

data class ExtratoVO(
    val contaResponsavel: ContaVO,
    val contaSaldo: ContaVO,
    val promocao: PromocaoVO? = null,
    @Serializable(with = LocalDateTimeSerializer::class) val dataCriacao: LocalDateTime? = null,
    val valor: Double,
    val isCredito: Boolean
)