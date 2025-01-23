package com.pscarpellini.extensions

import java.text.NumberFormat
import java.util.*

fun Double.formatarValorMonetario(): String {
    val formatoBR = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
    return formatoBR.format(this)
}