package com.pscarpellini.backend.enums.endpoints

import com.pscarpellini.backend.interfaces.IEndpointEnum
import kotlinx.html.FormMethod

enum class EndpointsRestritosEnum(
    override val path: String,
    override val method: FormMethod,
): IEndpointEnum {
    LoginRequest("login", FormMethod.post),

    ObterClientes("clientes", FormMethod.get),
    AdicionarCliente("adicionarCliente", FormMethod.post),
    AtualizarCliente("atualizarCliente", FormMethod.post),
    DesativarCliente("desativarCliente", FormMethod.post),

    ObterUsuarios("usuarios", FormMethod.get),
    CriarUserRequest("usuarios", FormMethod.post)
}