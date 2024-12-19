package com.pscarpellini.backend.enums.endpoints

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsNaoLogadosEnum(
    override val path: String,
    override val method: FormMethod
): IEndpointEnum {
    Ping("/ping", FormMethod.get),
    LoginRequest("/login", FormMethod.post),
}