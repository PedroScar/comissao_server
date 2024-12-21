package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface IFragmentEnum: ICaminho {
    override val path: String
    override val method: FormMethod

    override val pathCompleto: String
        get() = "/fragments/$path"
}