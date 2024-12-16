package com.pscarpellini.frontend.enums

import com.pscarpellini.frontend.pages.addUserPage
import com.pscarpellini.frontend.pages.homePage
import com.pscarpellini.frontend.pages.landingPage.landingPage
import com.pscarpellini.frontend.pages.loginPage.loginPage
import kotlinx.html.HTML

enum class PagesEnum(val path: String, val reference: HTML.() -> Unit) {
    Landing("/", HTML::landingPage),
    Login("/login", HTML::loginPage),
    Home("/home", HTML::homePage),
    AddUser("/addUser", HTML::addUserPage),
}