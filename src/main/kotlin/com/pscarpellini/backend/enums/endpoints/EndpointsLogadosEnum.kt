package com.pscarpellini.backend.enums.endpoints

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsLogadosEnum(
    override val path: String,
    override val method: FormMethod
): IEndpointEnum {
    LoginRequest("/login", FormMethod.post),
    CriarUserRequest("/addUser", FormMethod.post)
}