package com.pscarpellini.frontend.routes.restritos

import com.pscarpellini.frontend.interfaces.IPages

enum class RoutesRestritosEnum(
    override val path: String
): IPages {
    Home("/home"),
    AddUser("/addUser"),
    Logout("/logout"),
}