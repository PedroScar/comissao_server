package com.pscarpellini.backend.enums

import kotlinx.html.FormMethod

enum class EndpointsEnum(val path: String, val method: FormMethod) {
    LoginRequest("/login", FormMethod.post),
    CriarUserRequest("/addUser", FormMethod.post)
}