package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
import com.example.domain.model.requests.ApiUserIdRequest
import com.example.domain.model.response.ApiResponse
import com.example.domain.repository.UserDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.createSession(
    app: Application,

    userDataSource: UserDataSource

    ) {

   post(EndPoints.GetSessionId.path) {

        val request = call.receive<ApiUserIdRequest>()

       // app.log.info("request is ${request.userId}")
       app.log.info("Inside get request again")


        val user = userDataSource.getUserInfo(request.userId)

        if (user != null) {
            call.sessions.set(UserSession(id = user.ownerId, username = user.username, email = user.email))

            call.respond(
                ApiResponse(
                    success = true,
                    message = "Session Restored"
                )
            )


        } else {
            call.respondRedirect(EndPoints.Unauthorized.path)
        }


    }

}


