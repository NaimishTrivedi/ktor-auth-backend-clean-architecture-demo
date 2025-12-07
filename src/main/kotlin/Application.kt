package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.configureHTTP
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureServerError
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureServerError()
}
