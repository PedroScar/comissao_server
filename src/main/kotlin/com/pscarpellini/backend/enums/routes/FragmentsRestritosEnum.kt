package com.pscarpellini.backend.enums.routes

import com.pscarpellini.backend.interfaces.IPages

enum class FragmentsRestritosEnum(
    override val path: String,
): IPages {
    Menu("fragments/menu_principal"),
    HeaderDashboard("fragments/header_dashboard")
}