package com.pscarpellini.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatarIntervaloDeDatas(dataInicio: LocalDateTime, dataFim: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return "${dataInicio.format(formatter)} → ${dataFim.format(formatter)}"
}

fun LocalDateTime?.formatarData(): String {
    return this?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "Data inválida"
}
