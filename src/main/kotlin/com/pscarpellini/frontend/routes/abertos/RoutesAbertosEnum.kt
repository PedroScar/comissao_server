package com.pscarpellini.frontend.routes.abertos

import com.pscarpellini.frontend.interfaces.IPages

enum class RoutesAbertosEnum(
    override val path: String
): IPages {
    Landing("/"),
    Login("/login"),
}