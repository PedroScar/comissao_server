package com.pscarpellini.backend.models.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val emailId: Int? = null,
    val email: String? = null,
    val isCAS: Boolean? = null,
    var voucher: String? = null,
)