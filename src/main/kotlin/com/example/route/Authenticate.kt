package com.example.route

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticate(){

    authenticate {
    get("authenticate") {

        call.respond(HttpStatusCode.OK)

    }
}
}

