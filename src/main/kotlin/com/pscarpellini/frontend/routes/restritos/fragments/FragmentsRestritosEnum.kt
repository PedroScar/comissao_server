package com.pscarpellini.frontend.routes.restritos.fragments

import com.pscarpellini.frontend.interfaces.IPages

enum class FragmentsRestritosEnum(
    override val path: String,
): IPages {
    Menu("fragments/menu_principal"),
    HeaderDashboard("fragments/header_dashboard")
}