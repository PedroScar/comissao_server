package com.pscarpellini.frontend.routes.enums

import com.pscarpellini.frontend.interfaces.IPages
import com.pscarpellini.frontend.pages.logadas.addUserPage
import com.pscarpellini.frontend.pages.logadas.homePage
import kotlinx.html.HTML

enum class PagesLogadasEnum(
    override val path: String,
    override val reference: HTML.() -> Unit
): IPages {
    Home("/home", HTML::homePage),
    AddUser("/addUser", HTML::addUserPage),
}