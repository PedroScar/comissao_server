package enums

import kotlinx.html.FormMethod

enum class EndpointsEnum(val path: String, val method: FormMethod) {
    LoginRequest("/models/login", FormMethod.post),
    CriarUserRequest("/addUser", FormMethod.post)
}