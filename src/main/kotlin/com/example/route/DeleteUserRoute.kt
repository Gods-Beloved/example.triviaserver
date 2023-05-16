package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
//import com.example.domain.model.UserSession
import com.example.domain.model.response.ApiResponse
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*

fun Route.deleteUserRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    authenticate("auth-session") {
        delete(EndPoints.DeleteUser.path) {
            val userSession = call.principal<UserSession>()
          //  val principal = call.principal<JWTPrincipal>()
            val userId = userSession?.id
            if (userId == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(EndPoints.Unauthorized.path)
            } else {
                try {
                  // call.sessions.clear<UserSession>()
                    deleteUserFromDb(
                        app = app,
                        userId = userId,
                        userDataSource = userDataSource
                    )
                } catch (e: Exception) {
                    app.log.info("DELETING USER ERROR: ${e.message}")
                    call.respondRedirect(EndPoints.Unauthorized.path)
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.deleteUserFromDb(
    app: Application,
    userId: String   ,
    userDataSource: UserDataSource
) {
    val result = userDataSource.deleteUser(userId = userId)
    if (result) {
        app.log.info("USER SUCCESSFULLY DELETED")
        call.respond(
            message = ApiResponse(success = true),
            status = HttpStatusCode.OK
        )
    } else {
        app.log.info("ERROR DELETING THE USER")
        call.respond(
            message = ApiResponse(success = false),
            status = HttpStatusCode.BadRequest
        )
    }
}