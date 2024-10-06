package com.pscarpellini.enums

import com.pscarpellini.pages.addUserPage
import com.pscarpellini.pages.homePage
import com.pscarpellini.pages.loginPage
import kotlinx.html.HTML

enum class PagesEnum(val path: String, val reference: HTML.() -> Unit) {
    Login("/", HTML::loginPage),
    Home("/home", HTML::homePage),
    AddUser("/addUser", HTML::addUserPage),
}