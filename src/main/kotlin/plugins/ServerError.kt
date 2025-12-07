package com.example.plugins

import com.example.data.model.ApiResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.respond

fun Application.configureServerError() {
    install(StatusPages){
        exception<Throwable> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ApiResponse(
                    success = false,
                    message = cause.localizedMessage ?: "Unknown error",
                    data = null
                )
            )
        }

        exception<ContentTransformationException> { call, _ ->
            call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse(success = false,  message = "Invalid or missing Content-Type: application/json", data = null)
            )
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(
                HttpStatusCode.NotFound,
                ApiResponse(message = "Route not found", success = false, data = null)
            )
        }

        status(HttpStatusCode.UnsupportedMediaType) { call, status ->
            call.respond(
                HttpStatusCode.UnsupportedMediaType,
                ApiResponse(message = "Content-Type must be application/json", success = false, data = null)
            )
        }
    }
}