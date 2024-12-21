package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface IEndpointEnum: ICaminho {
    override val path: String
    override val method: FormMethod

    override val pathCompleto: String
        get() = "/api/$path"
}