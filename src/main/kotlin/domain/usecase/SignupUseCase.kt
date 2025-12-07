package com.example.domain.usecase

import com.example.data.model.ApiResponse
import com.example.data.model.UserData
import com.example.data.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt

class SignupUseCase(private val repo: UserRepository) {
    fun execute(name: String, email: String, password: String, mobile: String): ApiResponse<UserData> {
        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
        val userData = repo.insertUser(name, email, hashedPassword, mobile)
        if (userData != null) {
            return ApiResponse(
                success = true,
                data = userData,
                message = "Signup Successful!"
            )
        }

        return ApiResponse(
            success = false,
            message = "Email already in use!"
        )
    }
}