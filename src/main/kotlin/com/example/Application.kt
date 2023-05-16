package com.example
import io.ktor.server.application.*
import com.example.plugins.*
import com.example.security.hashing.SHA256HashingServiceImpl
import com.example.security.token.JwtTokenService
import com.example.security.token.TokenConfig

import io.ktor.server.application.*
import com.example.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 365L * 1000L * 60L * 60L * 24L,
        secret= "SrTBGXsMOzNBD0kE"
    )

    val hashingService = SHA256HashingServiceImpl()

    configureAuth()

    configureSecurity(tokenConfig)
    configureKoin()
    configureSession()
    configureMonitoring()
    configureSerialization()
    //configureRouting()
    configureRouting(hashingService = hashingService,tokenService=tokenService, tokenConfig = tokenConfig)
}
