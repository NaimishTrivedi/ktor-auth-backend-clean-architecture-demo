package com.example.domain.usecase

import com.example.data.model.ApiResponse
import com.example.data.model.UserData
import com.example.data.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    fun execute(email: String, password: String): ApiResponse<UserData> {

        val userData = repository.login(email, password)
        if (userData != null) {
            return ApiResponse(
                success = true,
                data = userData,
                message = "Login Successful!"
            )
        }

        return ApiResponse(
            success = false,
            message = "Email or Password corrupted!"
        )
    }
}