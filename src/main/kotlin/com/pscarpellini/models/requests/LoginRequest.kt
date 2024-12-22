package com.pscarpellini.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val usuario: String, val password: String)
