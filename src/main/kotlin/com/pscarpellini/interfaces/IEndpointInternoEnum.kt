package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface IEndpointInternoEnum: ICaminho {
    override val path: String
    override val method: FormMethod

    override val pathCompleto: String
        get() = "/interno/$path"
}