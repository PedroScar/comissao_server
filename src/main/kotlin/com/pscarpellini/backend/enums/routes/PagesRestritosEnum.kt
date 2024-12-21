package com.pscarpellini.backend.enums.routes

import com.pscarpellini.backend.interfaces.IPages

enum class PagesRestritosEnum(
    override val path: String
): IPages {
    Home("/home"),
    AddUser("/addUser"),
    Logout("/logout"),
}