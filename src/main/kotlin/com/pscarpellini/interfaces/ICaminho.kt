package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface ICaminho {
    val path: String
    val method: FormMethod
    val pathCompleto: String
}