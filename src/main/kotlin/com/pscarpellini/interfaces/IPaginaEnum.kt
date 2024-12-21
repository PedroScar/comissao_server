package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface IPaginaEnum: ICaminho {
    override val path: String
    override val method: FormMethod
        get() = FormMethod.get

    override val pathCompleto: String
        get() = "/$path"
}