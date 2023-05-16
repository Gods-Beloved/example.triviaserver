package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
//import com.example.domain.model.UserSession
import com.example.domain.model.response.ApiResponse
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getUserInfoRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    authenticate("auth-session") {
        get(EndPoints.GetUserInfo.path) {
           val userSession = call.principal<UserSession>()
//            val principal = call.principal<JWTPrincipal>()
           val userId = userSession?.id
            if (userId == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(EndPoints.Unauthorized.path)
            } else {
                try {
                    app.log.info("VALID SESSION $userId")

                    call.respond(
                        message = ApiResponse(
                            success = true,
                            triveaUser = userDataSource.getUserInfo(userId = userId)
                        ),
                        status = HttpStatusCode.OK
                    )
                } catch (e: Exception) {
                    app.log.info("GETTING USER INFO ERROR: ${e.message}")
                    call.respondRedirect(EndPoints.Unauthorized.path)
                }
            }
        }
    }
}