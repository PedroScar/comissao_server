package com.pscarpellini.frontend.routes.enums

import com.pscarpellini.frontend.interfaces.IPages
import com.pscarpellini.frontend.pages.nao_logadas.landingPage.landingPage
import com.pscarpellini.frontend.pages.nao_logadas.loginPage.loginPage
import com.pscarpellini.frontend.pages.nao_logadas.notFoundPage.notFoundPage
import kotlinx.html.HTML

enum class PagesNaoLogadasEnum(
    override val path: String,
    override val reference: HTML.() -> Unit
): IPages {
    Landing("/", HTML::landingPage),
    Login("/login", HTML::loginPage),
    NOT_FOUND_404("/login", HTML::notFoundPage),
}