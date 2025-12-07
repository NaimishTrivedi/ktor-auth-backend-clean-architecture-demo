package com.example.plugins

import com.example.di.appModule
import com.example.routes.authRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.plugin.Koin

fun Application.configureRouting() {
    install(Koin) {
        modules(appModule)
    }

    //Clean Architecture things
    routing {
        get("/") {
            call.respondText("Ktor Working 🚀")
        }

        authRoutes()
    }
}
