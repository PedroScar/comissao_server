package com.pscarpellini.backend.enums.endpoints

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsDeTesteEnum(
    override val path: String,
    override val method: FormMethod
): IEndpointEnum {
    CriarUserRequest("/addUser", FormMethod.post)
}