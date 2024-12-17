package com.pscarpellini.frontend.interfaces

import kotlinx.html.HTML

interface IPages {
    val path: String
    val reference: HTML.() -> Unit
}