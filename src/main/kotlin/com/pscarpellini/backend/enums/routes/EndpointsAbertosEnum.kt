package com.pscarpellini.backend.enums.routes

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsAbertosEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    LoginRequest("login", FormMethod.post),
}