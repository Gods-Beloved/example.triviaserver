package com.example.route

import com.example.domain.model.EndPoints
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.UnavailableQuestion(){

    get(EndPoints.NoAvailableQuestion.path) {

        call.respond(
            message = "No questions found"
        )

    }

}