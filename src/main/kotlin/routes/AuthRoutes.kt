package com.example.routes

import com.example.data.model.ApiResponse
import com.example.data.model.LoginRequest
import com.example.data.model.SignupRequest
import com.example.domain.usecase.LoginUseCase
import com.example.domain.usecase.SignupUseCase
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.authRoutes() {
    val loginUseCase by inject<LoginUseCase>()
    val signupUseCase by inject<SignupUseCase>()

    post("/login") {
        val request = call.receive<LoginRequest>()
        if (request.email.isBlank() || request.password.isBlank()) {
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Invalid email or password!",
                    data = null
                )
            )
            return@post
        }

        if (request.password.length < 6) {
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Password too short!",
                    data = null
                )
            )
            return@post
        }

        val result = loginUseCase.execute(request.email, request.password)
        call.respond(
            result
        )
    }

    post("/signup") {
        val req = call.receive<SignupRequest>()

        if(req.name.isBlank()){
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Name cannot be empty!",
                    data = null
                )
            )
            return@post
        }

        if(req.email.isBlank()){
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Email cannot be empty!",
                    data = null
                )
            )
            return@post
        }


        if(req.mobile.isBlank()){
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Mobile cannot be empty!",
                    data = null
                )
            )
            return@post
        }

        if(req.password.isBlank()){
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Password cannot be empty!",
                    data = null
                )
            )
            return@post
        }

        if (req.password.length < 6) {
            call.respond(
                ApiResponse(
                    success = false,
                    message = "Password too short!",
                    data = null
                )
            )
            return@post
        }

        val result = signupUseCase.execute(
            req.name,
            req.email,
            req.password,
            req.mobile
        )

        call.respond(result)
    }
}