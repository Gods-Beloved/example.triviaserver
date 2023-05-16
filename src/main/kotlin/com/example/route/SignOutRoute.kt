package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
import com.example.domain.model.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.signOutRoute() {
    authenticate("auth-session") {
        get(EndPoints.SignOut.path) {

             call.sessions.clear<UserSession>()
            call.respond(
           ApiResponse(
               success = true,
               message = "User successfully cleared"
           )
            )
        }
    }
}