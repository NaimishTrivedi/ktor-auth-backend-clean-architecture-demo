package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)
