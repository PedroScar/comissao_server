package com.pscarpellini.backend.enums.routes

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsControleEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    Ping("infos", FormMethod.get)
}