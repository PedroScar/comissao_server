package com.pscarpellini.interfaces

import kotlinx.html.FormMethod

interface IEndpointMobileEnum : ICaminho {
    override val path: String
    override val method: FormMethod

    override val pathCompleto: String
        get() = "/interno/$path"
}