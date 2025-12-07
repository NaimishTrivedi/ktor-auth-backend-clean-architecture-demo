package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id : String = "",
    val name: String,
    val email: String,
    val mobile: String,
)
