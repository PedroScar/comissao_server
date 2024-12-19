package com.pscarpellini.backend.interfaces

import kotlinx.html.FormMethod

interface IEndpointEnum {
    val path: String
    val method: FormMethod
}