package com.pscarpellini.backend.enums.routes

import com.pscarpellini.backend.interfaces.IPages

enum class PagesAbertosEnum(
    override val path: String
): IPages {
    Landing("/"),
    Login("/login"),
}